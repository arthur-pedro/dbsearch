package com.dbsearch.api.core.database.where;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class WhereBuilder {
		private final List<Clause> clauses = new ArrayList<>();

		public WhereBuilder add(Clause clause) {
				this.clauses.add(clause);
				return this;
		}

		public Where build() {
				String formattedClauses = clauses.stream()
								.map(Clause::toString)
								.collect(Collectors.joining(" AND "));
				return new Where(formattedClauses);
		}
}
