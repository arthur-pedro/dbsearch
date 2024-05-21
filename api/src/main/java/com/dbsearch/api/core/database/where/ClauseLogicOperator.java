package com.dbsearch.api.core.database.where;

import lombok.Getter;

@Getter
public enum ClauseCondition {
		AND("AND"),
		OR("OR"),
		JOIN("JOIN");

		private final String value;

		ClauseCondition(String value) {
				this.value = value;
		}
}
