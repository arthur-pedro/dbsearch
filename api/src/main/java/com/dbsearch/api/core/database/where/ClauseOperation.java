package com.dbsearch.api.core.database.where;

import lombok.Getter;

@Getter
public enum ClauseOperation {
		GREATER_THAN(">"),
		LESS_THAN("<"),
		GREATER_THAN_OR_EQUALS(">="),
		LESS_THAN_OR_EQUALS("<= "),
		NOT_EQUALS("!="),
		LIKE("LIKE"),
		IS_NULL("IS NULL"),
		IS_NOT_NULL("IS NOT NULL"),
		OR("OR"),
		EQUALS("=");

		private final String value;

		ClauseOperation(String value) {
				this.value = value;
		}
}
