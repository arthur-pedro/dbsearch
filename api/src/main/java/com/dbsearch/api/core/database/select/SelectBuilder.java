package com.dbsearch.api.core.database.select;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SelectBuilder {
		private final List<Column> columns = new ArrayList<>();
		private final Blocklist blocklist;

		public SelectBuilder() {
				this.blocklist = new Blocklist();
		}

		public SelectBuilder add(Column column) {
				if (blocklist.IsBlocked(column)) {
						this.columns.add(column);
				}
				return this;
		}

		public Select build() {
				String formattedFields = columns.stream()
								.map(Column::getName)
								.collect(Collectors.joining(", "));
				return new Select(formattedFields);
		}
}
