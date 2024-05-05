package com.dbsearch.api.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class DatabaseRepository {
    private EntityManager entityManager;

    public DatabaseRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    public List<String> getSchemas(String databaseName) {
        String sql = "SELECT schema_name as name FROM information_schema.schemata WHERE schemata.catalog_name = '" + databaseName + "'";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

}
