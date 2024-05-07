package com.dbsearch.api.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;


@Repository
@Transactional
public class ConnectionRepository {
    private EntityManager entityManager;

    public ConnectionRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
    public List<String> getDatabases(String databaseName) {
        String sql = "SELECT datname AS name FROM pg_catalog.pg_database WHERE datistemplate = false AND datname = '"+ databaseName +"' ORDER BY name;";
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

}
