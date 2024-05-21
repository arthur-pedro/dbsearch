package com.dbsearch.api.core.database.where;

import lombok.Getter;

@Getter
public enum ClauseLogicOperator {
		AND("AND"),
		OR("OR"),
		JOIN("JOIN");

		private final String value;

		ClauseLogicOperator(String value) {
				this.value = value;
		}
}
