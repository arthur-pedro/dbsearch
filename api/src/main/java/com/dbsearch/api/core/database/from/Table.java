package com.dbsearch.api.core.database.from;

import lombok.Getter;

@Getter
public class Table {
		private final String name;
		private final String alias;

		public Table(String name) {
				this.name = name;
				this.alias = this.buildAlias();
		}

		private String buildAlias() {
				return this.name.substring(0, 1);
		}
}
