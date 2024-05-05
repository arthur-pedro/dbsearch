package com.dbsearch.api.dto;



import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class TableDataDTO {
    private String tableName;
    private List<String> columns;
    private List<Map<String, Object>> data;
}
