package com.dbsearch.api.repository.connection;


import com.dbsearch.api.core.database.from.FromBuilder;
import com.dbsearch.api.core.database.from.Table;
import com.dbsearch.api.core.database.order.OrderBuilder;
import com.dbsearch.api.core.database.order.OrderDirection;
import com.dbsearch.api.core.database.query.QueryBuilder;
import com.dbsearch.api.core.database.select.Column;
import com.dbsearch.api.core.database.select.SelectBuilder;
import com.dbsearch.api.core.database.where.Clause;
import com.dbsearch.api.core.database.where.ClauseConditionOperator;
import com.dbsearch.api.core.database.where.ClauseLogicOperator;
import com.dbsearch.api.core.database.where.WhereBuilder;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Transactional
public class ConnectionRepository {
		private final EntityManager entityManager;

		public ConnectionRepository(EntityManager entityManager) {
				this.entityManager = entityManager;
		}

		@SuppressWarnings("unchecked")
		public List<String> getDatabases(String databaseName) {
				QueryBuilder queryBuilder = new QueryBuilder();

				SelectBuilder selectBuilder = new SelectBuilder();
				selectBuilder.add(new Column("datname"));

				FromBuilder fromBuilder = new FromBuilder();
				Table table = new Table("pg_catalog.pg_database");
				fromBuilder.add(table);

				WhereBuilder whereBuilder = new WhereBuilder();
				Clause datistemplateClause = new Clause(
								table,
								new Column("datistemplate"),
								ClauseConditionOperator.EQUALS,
								ClauseLogicOperator.AND,
								Boolean.FALSE.toString()
				);
				Clause datnameClause = new Clause(
								table,
								new Column("datname"),
								ClauseConditionOperator.EQUALS,
								ClauseLogicOperator.AND,
								databaseName
				);
				whereBuilder
								.add(datistemplateClause)
								.add(datnameClause);

				OrderBuilder orderBuilder = new OrderBuilder();
				orderBuilder.add(
								table,
								new Column("datname"),
								OrderDirection.ASC);

				queryBuilder
								.select(selectBuilder)
								.from(fromBuilder)
								.where(whereBuilder)
								.order(orderBuilder);

				String sql = queryBuilder.build();
				Query query = entityManager.createNativeQuery(sql);
				return query.getResultList();
		}

}
