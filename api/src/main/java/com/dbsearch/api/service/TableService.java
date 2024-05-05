package com.dbsearch.api.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dbsearch.api.dto.TableDataDTO;
import com.dbsearch.api.dto.TableInfoDTO;
import com.dbsearch.api.dto.TableSearchDTO;
import com.dbsearch.api.repository.TableRepository;
import org.springframework.stereotype.Service;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;


@Service
@Transactional
public class TableService {
    @PersistenceContext
    private EntityManager entityManager;

    public List<TableInfoDTO> findTableNames(String schema) {
        TableRepository tableRepository = new TableRepository(entityManager);
        return tableRepository.findTableNames(schema)
            .stream()
            .map(name -> new TableInfoDTO(name, new ArrayList<>()))
            .toList();
    }

    public TableInfoDTO findTableColumns(String tableName, String schema) {
        TableRepository tableRepository = new TableRepository(entityManager);
        TableInfoDTO table = new TableInfoDTO(tableName, new ArrayList<>());
        tableRepository.findColumnsFromTable(tableName, schema)
            .stream()
            .map(column -> table.getColumns().add(column))
            .toList();
        return table;
    }

    public TableDataDTO searchInTable(TableSearchDTO search) {
        TableRepository tableRepository = new TableRepository(entityManager);
        List<String> columnNames = findTableColumns(search.getTableName(), search.getSchema()).getColumns();
        if (search.getFields() == null || search.getFields().isEmpty()) {
            search.setFields(columnNames);
        }
        List<Object[]> results = tableRepository.search(
            search.getTableName(), 
            search.getSchema(), 
            search.getFields(), 
            search.getPage(), 
            search.getPageSize(),
            search.getWhere());
        List<Map<String, Object>> mappedResults = new ArrayList<>();
        for (Object[] row : results) {
            Map<String, Object> rowMap = new HashMap<>();
            for (int i = 0; i < row.length; i++) {
                rowMap.put(columnNames.get(i), row[i]);
            }
            mappedResults.add(rowMap);
        }
        return new TableDataDTO(search.getTableName(), columnNames, mappedResults);
    }
}