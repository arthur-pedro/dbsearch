package com.dbsearch.api.core.database.order;

import com.dbsearch.api.core.database.column.Column;
import com.dbsearch.api.core.database.from.Table;
import lombok.Getter;

@Getter
public class OrderBuilder {
		private Table table; // TODO: Change to List<Table> tables
		private Column column;
		private OrderDirection direction;

		public OrderBuilder add(Table table, Column column, OrderDirection direction) {
				this.table = table;
				this.column = column;
				this.direction = direction;
				return this;
		}

		public Order build() {
				return new Order(table.getAlias() + "." + column.getName() + " " + direction.getValue());
		}

}
