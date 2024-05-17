package com.dbsearch.api.core.database.select;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {
		private String name;
		private String alias;

		public Column(String name) {
				this.name = name;
				this.alias = this.buildAlias(this.name);
		}

		private String buildAlias(String name) {
				return "" + name.toUpperCase().charAt(0) + name.toUpperCase().charAt(name.length() - 1);
		}
}
