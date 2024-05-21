package com.dbsearch.api.repository.table;


import com.dbsearch.api.core.database.from.FromBuilder;
import com.dbsearch.api.core.database.from.Table;
import com.dbsearch.api.core.database.pagination.Page;
import com.dbsearch.api.core.database.pagination.PaginationBuilder;
import com.dbsearch.api.core.database.query.CountBuilder;
import com.dbsearch.api.core.database.query.QueryBuilder;
import com.dbsearch.api.core.database.select.Column;
import com.dbsearch.api.core.database.select.SelectBuilder;
import com.dbsearch.api.core.database.where.Clause;
import com.dbsearch.api.core.database.where.ClauseConditionOperator;
import com.dbsearch.api.core.database.where.ClauseLogicOperator;
import com.dbsearch.api.core.database.where.WhereBuilder;
import com.dbsearch.api.core.dto.FilterDTO;
import com.dbsearch.api.core.dto.PaginationDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public class TableRepository {
		private final EntityManager entityManager;

		public TableRepository(EntityManager entityManager) {
				this.entityManager = entityManager;
		}

		@SuppressWarnings("unchecked")
		public List<String> findTableNames(String schema) {
				QueryBuilder queryBuilder = new QueryBuilder();

				queryBuilder.select(
								new SelectBuilder().add(new Column("tablename"))
				);
				queryBuilder.from(
								new FromBuilder().add(new Table("pg_tables")))
				;
				queryBuilder.where(
								new WhereBuilder().add(
												new Clause(
																new Table("pg_tables"),
																new Column("schemaname"), ClauseConditionOperator.EQUALS, ClauseLogicOperator.AND,
																schema)
								));
				String sql = queryBuilder.build();
				Query query = entityManager.createNativeQuery(sql);
				return query.getResultList();
		}

		@SuppressWarnings("unchecked")
		public List<String> findColumnsFromTable(String tableName, String schema) {
				QueryBuilder queryBuilder = new QueryBuilder();

				SelectBuilder selectBuilder = new SelectBuilder();
				selectBuilder.add(new Column("column_name"));

				FromBuilder fromBuilder = new FromBuilder();
				fromBuilder.add(new Table("information_schema.columns"));

				WhereBuilder whereBuilder = new WhereBuilder();

				whereBuilder.add(
								new Clause(fromBuilder.getTable(), new Column("table_name"), ClauseConditionOperator.EQUALS,
												ClauseLogicOperator.AND, tableName));
				whereBuilder.add(
								new Clause(fromBuilder.getTable(), new Column("table_schema"), ClauseConditionOperator.EQUALS,
												ClauseLogicOperator.AND, schema));

				queryBuilder
								.select(selectBuilder)
								.from(fromBuilder)
								.where(whereBuilder);

				String sql = queryBuilder.build();
				Query query = entityManager.createNativeQuery(sql);
				return query.getResultList();
		}

		@SuppressWarnings("unchecked")
		public PaginationDTO<Object> search(
						String tableName,
						List<String> columns,
						int page,
						int pageSize,
						List<FilterDTO> filters
		) {
				QueryBuilder queryBuilder = new QueryBuilder();

				SelectBuilder selectBuilder = new SelectBuilder();
				columns.forEach(column->selectBuilder.add(new Column(column)));

				FromBuilder fromBuilder = new FromBuilder();
				Table table = new Table(tableName);
				table.setCamelCase();
				fromBuilder.add(table);

				WhereBuilder whereBuilder = new WhereBuilder();
				filters.forEach((filter)->{
						Clause clause = new Clause(
										table,
										new Column(filter.getColumn()),
										filter.getConditionOperator(),
										filter.getLogicOperator(),
										filter.getValue());
						whereBuilder.add(clause);
				});

				PaginationBuilder paginationBuilder = new PaginationBuilder();
				paginationBuilder.add(new Page(page, pageSize));

				queryBuilder
								.select(selectBuilder)
								.from(fromBuilder)
								.where(whereBuilder)
								.page(paginationBuilder);

				String sql = queryBuilder.build();
				Query query = this.entityManager.createNativeQuery(sql);

				int totalElements = this.count(tableName, filters);

				return new PaginationDTO<Object>(
								query.getResultList(),
								totalElements,
								page,
								pageSize
				);
		}

		public int count(String tableName, List<FilterDTO> filters) {
				Table table = new Table(tableName);
				table.setCamelCase();

				FromBuilder fromBuilder = new FromBuilder();
				fromBuilder.add(table);

				WhereBuilder whereBuilder = new WhereBuilder();
				filters.forEach((filter)->{
						Clause clause = new Clause(
										table,
										new Column(filter.getColumn()),
										filter.getConditionOperator(),
										filter.getLogicOperator(),
										filter.getValue());
						whereBuilder.add(clause);
				});

				String sql = new CountBuilder()
								.from(fromBuilder)
								.where(whereBuilder)
								.build();
				Query query = this.entityManager.createNativeQuery(sql);
				return ((Number) query.getSingleResult()).intValue();
		}
}
