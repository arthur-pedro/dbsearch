package com.dbsearch.api.core.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Data
@Getter
@Setter
@AllArgsConstructor
public class TableDataDTO {
		private String tableName;
		private List<String> columns;
		private List<Map<String, Object>> data;
		private int totalData;
		private int page;
		private int size;
}
