package com.dbsearch.api.core.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
public class DatabaseDTO {
		private String id;
		private String name;
		private String description;
		private List<SchemaDTO> schemas;
}
