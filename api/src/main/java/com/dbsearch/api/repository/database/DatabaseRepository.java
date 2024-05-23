package com.dbsearch.api.repository.database;


import com.dbsearch.api.core.database.column.Column;
import com.dbsearch.api.core.database.from.FromBuilder;
import com.dbsearch.api.core.database.from.Table;
import com.dbsearch.api.core.database.query.QueryBuilder;
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
public class DatabaseRepository {
		private final EntityManager entityManager;

		public DatabaseRepository(EntityManager entityManager) {
				this.entityManager = entityManager;
		}

		@SuppressWarnings("unchecked")
		public List<String> getSchemas(String databaseName) {
				QueryBuilder queryBuilder = new QueryBuilder();

				Table informationSchemaTable = new Table("information_schema.schemata");
				informationSchemaTable.addColumn(new Column("schema_name"));
				Column catalogNameColumn = new Column("catalog_name");
				catalogNameColumn.setSelectable(false);
				informationSchemaTable.addColumn(catalogNameColumn);


				SelectBuilder selectBuilder = new SelectBuilder();
				selectBuilder.add(informationSchemaTable);

				FromBuilder fromBuilder = new FromBuilder();
				fromBuilder.add(informationSchemaTable);

				WhereBuilder whereBuilder = new WhereBuilder();
				Clause clause = new Clause(
								informationSchemaTable,
								informationSchemaTable.getColumn("catalog_name"),
								ClauseConditionOperator.EQUALS,
								ClauseLogicOperator.AND,
								databaseName);
				whereBuilder.add(clause);

				queryBuilder
								.select(selectBuilder)
								.from(fromBuilder)
								.where(whereBuilder);

				String sql = queryBuilder.build();
				Query query = entityManager.createNativeQuery(sql);
				return query.getResultList();
		}

}
