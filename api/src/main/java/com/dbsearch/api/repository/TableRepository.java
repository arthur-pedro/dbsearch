package com.dbsearch.api.repository;


import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.dbsearch.api.config.database.from.From;
import com.dbsearch.api.config.database.query.QueryBuilder;
import com.dbsearch.api.config.database.select.Blocklist;
import com.dbsearch.api.config.database.select.Field;
import com.dbsearch.api.config.database.select.SelectBuilder;
import com.dbsearch.api.config.database.where.Where;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class TableRepository {
    private EntityManager entityManager;

    public TableRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    public List<String> findTableNames(String schema) {
        String sql = "SELECT tablename as name FROM pg_tables WHERE schemaname = '" + schema + "'";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<String> findColumnsFromTable(String tableName, String schema) {
        Blocklist blocklist = new Blocklist();

        SelectBuilder selectBuilder = new SelectBuilder(blocklist);
        selectBuilder.add(new Field("column_name"));

        QueryBuilder queryBuilder = new QueryBuilder();
        queryBuilder.select(selectBuilder.build());
        queryBuilder.from(new From("information_schema.columns"));
        queryBuilder.where(new Where("table_name = '" + tableName + "' AND table_schema = '" + schema + "'"));

        Query query = entityManager.createNativeQuery(queryBuilder.build());
        return query.getResultList();
    }

    @SuppressWarnings("unchecked")
    public List<Object[]> search(String tableName, String schema, List<String> fields, int page, int pageSize, Map<String, String> where) {
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
        String selectFields = String.join(", ", fields.stream().map(field -> "\"" + field + "\"").toList());
        String limit = " LIMIT " + pageSize + " OFFSET " + (page - 1) * pageSize;
        String sql = "SELECT " + selectFields + " FROM " + schema + ".\"" + tableName + "\"" + whereClouse + limit;
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    public static String createWhereClause(Map<String, String> filters) {
        return filters.entrySet()
                .stream()
                .map(entry -> "\""+ entry.getKey() + "\" " + "  ILIKE '%" + entry.getValue() + "%'")
                .collect(Collectors.joining(" AND "));
    }

}
