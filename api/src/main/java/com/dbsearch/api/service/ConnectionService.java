package com.dbsearch.api.service;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.dbsearch.api.config.tenant.TenantPropertyFile;
import com.dbsearch.api.config.tenant.TenantContext;
import com.dbsearch.api.dto.ConnectionDTO;
import com.dbsearch.api.dto.DatabaseDTO;
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
        assert files != null;
        for (File file : files) {
            TenantPropertyFile tenantPropertyFile = new TenantPropertyFile(file);
            result.add(new ConnectionDTO(
                tenantPropertyFile.getTenantId(),
                tenantPropertyFile.getName(),
                tenantPropertyFile.getDescription(),
                tenantPropertyFile.getHost(),
                tenantPropertyFile.getDriverClassName(),
                tenantPropertyFile.getEnvironment(),
                new ArrayList<>())
            );
        }
        return result;
    }
    
    public List<DatabaseDTO> getDatabases() {
        List<DatabaseDTO> result = new ArrayList<>();
        ConnectionRepository connectionRepository = new ConnectionRepository(entityManager);
        TenantPropertyFile tenantPropertyFile = new TenantPropertyFile(TenantContext.getCurrentTenantFile());
        String databaseName = tenantPropertyFile.getDatabase();
        List<String> databases = connectionRepository.getDatabases(databaseName);
        for (String database : databases) {
            DatabaseDTO databaseDTO = new DatabaseDTO(database, database, "Descrição não encontrada", new ArrayList<>());
            databaseDTO.setName(database);
            result.add(databaseDTO);
        }
        return result;
    }
}