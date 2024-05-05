package com.dbsearch.api.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.dbsearch.api.dto.ConnectionDTO;
import com.dbsearch.api.dto.DatabaseDTO;
import com.dbsearch.api.dto.SchemaDTO;
import org.springframework.stereotype.Service;

import com.dbsearch.api.repository.ConnectionRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class ConnectionService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<ConnectionDTO> list() {
        List<ConnectionDTO> result = new ArrayList<>();
        File[] files = Paths.get("api/tenants").toFile().listFiles();
        for (File propertyFile : files) {
            Properties tenantProperties = new Properties();
            try {
                tenantProperties.load(new FileInputStream(propertyFile));
                result.add(new ConnectionDTO(
                    tenantProperties.getProperty("id"), 
                    tenantProperties.getProperty("name"), 
                    tenantProperties.getProperty("description"), 
                    tenantProperties.getProperty("datasource.url"), 
                    tenantProperties.getProperty("datasource.driver-class-name"), 
                    new ArrayList<>()));
            } catch (IOException exp) {
                throw new RuntimeException("Problem in tenant datasource:" + exp);
            }
        }
        return result;
    }
    
    public List<DatabaseDTO> getDatabases() {
        List<DatabaseDTO> result = new ArrayList<>();
        ConnectionRepository connectionRepository = new ConnectionRepository(entityManager);
        List<String> databases = connectionRepository.getDatabases();
        for (String database : databases) {
            DatabaseDTO databaseDTO = new DatabaseDTO(database, database, "Descrição não encontrada", new ArrayList<>());
            databaseDTO.setName(database);
            result.add(databaseDTO);
        }
        return result;
    }
}