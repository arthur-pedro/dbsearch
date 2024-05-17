package com.dbsearch.api.core.database.select;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public class SelectBuilder {
		private final List<Field> fields = new ArrayList<>();
		private final Blocklist blocklist;

		public SelectBuilder() {
				this.blocklist = new Blocklist();
		}

		public SelectBuilder add(Field field) {
				if (blocklist.IsBlocked(field)) {
						this.fields.add(field);
				}
				return this;
		}

		public Select build() {
				String formattedFields = fields.stream()
								.map(Field::getName)
								.collect(Collectors.joining(", "));
				return new Select(formattedFields);
		}
}
