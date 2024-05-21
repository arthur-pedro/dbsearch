package com.dbsearch.api.core.database.query;

import com.dbsearch.api.core.database.from.From;
import com.dbsearch.api.core.database.from.FromBuilder;
import com.dbsearch.api.core.database.where.Where;
import com.dbsearch.api.core.database.where.WhereBuilder;

public class CountBuilder {
		private From from;
		private Where where;

		public CountBuilder from(FromBuilder value) {
				this.from = value.build();
				return this;
		}

		public CountBuilder where(WhereBuilder value) {
				this.where = value.build();
				return this;
		}

		public String build() {
				return String.format("SELECT COUNT(*) FROM %s WHERE 1=1 %s",
								from.value(),
								where.value());
		}
}
