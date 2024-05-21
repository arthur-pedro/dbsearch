package com.dbsearch.api.core.database.where;

import com.dbsearch.api.core.exceptions.BusinessException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class WhereBuilder {
		private final List<Clause> clauses = new ArrayList<>();

		public WhereBuilder add(Clause clause) {
				if (clause == null) {
						throw new BusinessException("Clause in WhereBuilder cannot be null");
				}
				if (clause.getValue() != null && !clause.getValue().isEmpty()) {
						this.clauses.add(clause);
				}
				return this;
		}

		public Where build() {
				String formattedClauses = clauses.stream()
								.map(Clause::toString)
								.reduce((clause1, clause2)->clause1 + " " + clause2)
								.orElse("");
				return new Where(formattedClauses);
		}
}
