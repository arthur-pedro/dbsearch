package com.dbsearch.api.repository.export;


import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;


@Repository
@Transactional
public class ExportRepository {
		private EntityManager entityManager;

		public ExportRepository(EntityManager entityManager) {
				this.entityManager = entityManager;
		}

//    @SuppressWarnings("unchecked")
//    public List<String> findColumnsFromTable(String tableName) {
//        SelectBuilder selectBuilder = new SelectBuilder();
//        selectBuilder.add(new Field("column_name"));
//
//        QueryBuilder queryBuilder = new QueryBuilder();
//        queryBuilder.select(selectBuilder.build());
//        queryBuilder.from(new From("information_schema.columns"));
//        queryBuilder.where(new Where("table_name = '" + tableName + "' AND table_schema = '" + schema + "'"));
//
//        Query query = entityManager.createNativeQuery(
//                new StringBuilder()
//                        .append("SELECT id,text,price into OUTFILE data_export FIELDS TERMINATED BY ',' FROM ")
//                        .append(tableName)
//                        .append(" t")
//                        .toString());
//        return query.getResultList();
//    }

}
