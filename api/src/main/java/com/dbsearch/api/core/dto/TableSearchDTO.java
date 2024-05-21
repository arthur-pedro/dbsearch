package com.dbsearch.api.core.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TableSearchDTO {
		@NotNull(message = "Table name is required")
		private String tableName;

		@NotNull(message = "Schema is required")
		private String schema;

		@NotNull(message = "Page is required")
		@Size(min = 1, message = "Page must be greater than 0")
		private int page;

		@NotNull(message = "Page size is required")
		@Size(min = 1, message = "Page size must be greater than 0")
		private int pageSize;

		@Valid
		@NotNull(message = "Columns are required")
		@Size(min = 1, message = "Select at least one column")
		private List<String> columns;

		@Valid
		private List<FilterDTO> filters;
}
