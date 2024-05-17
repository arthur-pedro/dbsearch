package com.dbsearch.api.repository.export;


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


@Repository
@Transactional
public class ExportRepository {
		private final EntityManager entityManager;

		public ExportRepository(EntityManager entityManager) {
				this.entityManager = entityManager;
		}

		@SuppressWarnings("unchecked")
		public List<Object[]> export(
						String tableName,
						List<String> columns,
						Map<Column, String> filters
		) {
				QueryBuilder queryBuilder = new QueryBuilder();

				SelectBuilder selectBuilder = new SelectBuilder();
				columns.forEach(column->selectBuilder.add(new Column(column)));

				FromBuilder fromBuilder = new FromBuilder();
				Table table = new Table(tableName);
				fromBuilder.add(table);

				WhereBuilder whereBuilder = new WhereBuilder();
				filters.forEach((key, value)->{
						Clause clause = new Clause(table, key, ClauseOperation.EQUALS, value);
						whereBuilder.add(clause);
				});

				queryBuilder
								.select(selectBuilder)
								.from(fromBuilder)
								.where(whereBuilder);

				String sql = queryBuilder.build();
				Query query = this.entityManager.createNativeQuery(sql);
				return query.getResultList();
		}
}
