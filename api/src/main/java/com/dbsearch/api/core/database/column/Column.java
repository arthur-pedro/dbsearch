package com.dbsearch.api.core.database.column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Column {
		private String name;
		private String alias;
		private boolean selectable = true;

		public Column(String name) {
				this.name = name;
				this.alias = this.buildAlias(this.name);
		}

		private String buildAlias(String name) {
				return "" + name.toUpperCase().charAt(0) + name.toUpperCase().charAt(name.length() - 1);
		}

		public String getFormatedName() {
				return String.format("\"%s\"", this.name);
		}
}
