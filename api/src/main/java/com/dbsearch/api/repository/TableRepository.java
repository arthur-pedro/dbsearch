package com.dbsearch.api.repository;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
        String sql = "SELECT column_name FROM information_schema.columns WHERE table_name = '" + tableName + "' AND table_schema = '" + schema + "'";
        Query query = entityManager.createNativeQuery(sql);
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
