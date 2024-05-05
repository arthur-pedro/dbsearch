package com.dbsearch.api.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TableSearchDTO {
    private String tableName;
    private String schema;
    private int page;
    private int pageSize;
    private List<String> fields;
    private Map<String, String> where;
}
