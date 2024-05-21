package com.dbsearch.api.core.database.from;

import lombok.Getter;

@Getter
public class FromBuilder {
		private Table table;

		public FromBuilder add(Table table) {
				this.table = table;
				return this;
		}

		public From build() {
				return new From(table.getName() + " " + table.getAlias());
		}
}
