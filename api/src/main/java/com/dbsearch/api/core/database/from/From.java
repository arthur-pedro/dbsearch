package com.dbsearch.api.core.database.from;

public record From(Table table) {
		public String value() {
				return this.from() + " " + this.join();
		}

		private String from() {
				return table.getName() + " " + table.getAlias();
		}

		private String join() {
				StringBuilder join = new StringBuilder();
				table.getJoins().forEach((key, value)->{
						join.append(" ")
										.append(value.getJoinType().value())
										.append(" ")
										.append(key.getName())
										.append(" ")
										.append(key.getAlias())
										.append(" ON ")
										.append(value.getColumn().getName());
				});
				return join.toString();
		}
}
