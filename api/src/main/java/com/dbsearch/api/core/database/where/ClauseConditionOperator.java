package com.dbsearch.api.core.database.where;

import lombok.Getter;

@Getter
public enum ClauseConditionOperator {
		GREATER_THAN(">"),
		LESS_THAN("<"),
		GREATER_THAN_OR_EQUALS(">="),
		LESS_THAN_OR_EQUALS("<= "),
		NOT_EQUALS("!="),
		LIKE("LIKE"),
		ILIKE("ILIKE"),
		IS_NULL("IS NULL"),
		IS_NOT_NULL("IS NOT NULL"),
		EQUALS("="),
		IN("IN"),
		NOT_IN("NOT IN"),
		BETWEEN("BETWEEN");

		private final String value;

		ClauseConditionOperator(String value) {
				this.value = value;
		}
}
