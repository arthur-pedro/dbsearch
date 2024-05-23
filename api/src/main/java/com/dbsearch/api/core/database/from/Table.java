package com.dbsearch.api.core.database.from;

import com.dbsearch.api.core.database.column.Column;
import com.dbsearch.api.core.database.join.JoinKey;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class Table {
		private final String alias;
		private final Map<Table, JoinKey> joins = new HashMap<>();
		private final List<Column> columns = new ArrayList<>();
		private String name;
		@Setter private String schema;

		public Table(String name) {
				this.name = name;
				this.alias = this.buildAlias();
		}

		private String buildAlias() {
				return this.name.substring(0, 1);
		}


		public void setCamelCase() {
				this.name = String.format("\"%s\"", this.name);
		}

		public void addColumn(List<Column> columns) {
				this.columns.addAll(columns);
		}

		public void addColumn(Column column) {
				this.columns.add(column);
		}

		public Column getColumn(String name) {
				return this.columns.stream()
								.filter(column->column.getName().trim().equals(name.trim()))
								.findFirst()
								.orElse(null);
		}
}
