package com.dbsearch.api.core.database.select;

import com.dbsearch.api.core.database.column.Column;
import com.dbsearch.api.core.database.from.Table;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SelectBuilder {
		private final List<Column> columns = new ArrayList<>();
		private final Blocklist blocklist;

		public SelectBuilder() {
				this.blocklist = new Blocklist();
		}

		public SelectBuilder add(Table table) {
				table.getColumns().forEach(this::pickColumn);
				return this;
		}

		private void pickColumn(Column column) {
				if (column.isSelectable() && blocklist.IsBlocked(column)) {
						this.columns.add(column);
				}
		}

		public Select build() {
				return new Select(this.columns);
		}
}
