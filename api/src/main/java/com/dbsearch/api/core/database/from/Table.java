package com.dbsearch.api.core.database.from;

import lombok.Getter;
import lombok.Setter;

@Getter
public class Table {
		private final String alias;
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
}
