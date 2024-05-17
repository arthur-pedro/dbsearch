package com.dbsearch.api.repository.table;


import com.dbsearch.api.core.database.from.FromBuilder;
import com.dbsearch.api.core.database.from.Table;
import com.dbsearch.api.core.database.query.QueryBuilder;
import com.dbsearch.api.core.database.select.Column;
import com.dbsearch.api.core.database.select.SelectBuilder;
import com.dbsearch.api.core.database.where.Clause;
import com.dbsearch.api.core.database.where.ClauseOperation;
import com.dbsearch.api.core.database.where.WhereBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
																new Column("schemaname"), ClauseOperation.EQUALS, schema)
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
								new Clause(fromBuilder.getTable(), new Column("table_name"), ClauseOperation.EQUALS, tableName));
				whereBuilder.add(
								new Clause(fromBuilder.getTable(), new Column("table_schema"), ClauseOperation.EQUALS, schema));

				queryBuilder
								.select(selectBuilder)
								.from(fromBuilder)
								.where(whereBuilder);

				String sql = queryBuilder.build();
				Query query = entityManager.createNativeQuery(sql);
				return query.getResultList();
		}

		@SuppressWarnings("unchecked")
		public List<Object[]> search(String tableName, String schema, List<String> fields, int page, int pageSize,
		                             Map<String, String> where) {
				if (page < 1) {
						page = 1;
				}
				if (pageSize < 1) {
						pageSize = 10;
				}
				String whereClouse = "";
				if (where != null && !where.isEmpty()) {
						whereClouse = " WHERE 1=1 AND " + createWhereClause(where);
				}
				String selectFields = String.join(", ", fields.stream().map(field->"\"" + field + "\"").toList());
				String limit = " LIMIT " + pageSize + " OFFSET " + (page - 1) * pageSize;
				String sql = "SELECT " + selectFields + " FROM " + schema + ".\"" + tableName + "\"" + whereClouse + limit;
				Query query = entityManager.createNativeQuery(sql);
				return query.getResultList();
		}

		public static String createWhereClause(Map<String, String> filters) {
				return filters.entrySet()
								.stream()
								.map(entry->"\"" + entry.getKey() + "\" " + "  ILIKE '%" + entry.getValue() + "%'")
								.collect(Collectors.joining(" AND "));
		}

}
