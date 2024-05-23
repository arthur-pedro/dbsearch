package com.dbsearch.api.core.database.join;

public enum JoinType {
		INNER_JOIN("INNER JOIN"),
		LEFT_JOIN("LEFT JOIN"),
		RIGHT_JOIN("RIGHT JOIN"),
		FULL_JOIN("FULL JOIN");

		private final String value;

		JoinType(String value) {
				this.value = value;
		}

		public String value() {
				return this.value;
		}
}
