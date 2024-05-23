package com.dbsearch.api.core.database.select;

import com.dbsearch.api.core.database.column.Column;

import java.util.List;
import java.util.stream.Collectors;

public record Select(List<Column> columns) {
		public String value() {
				return columns.stream()
								.map(Column::getFormatedName)
								.collect(Collectors.joining(", "));
		}
}
