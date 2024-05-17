package com.dbsearch.api.core.database.order;

import lombok.Getter;

@Getter
public enum OrderDirection {
		DESC("DESC"),
		ASC("ASC");

		private final String value;

		OrderDirection(String value) {
				this.value = value;
		}
}
