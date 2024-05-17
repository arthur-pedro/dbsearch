package com.dbsearch.api.core.database.order;

import com.dbsearch.api.core.database.from.Table;
import com.dbsearch.api.core.database.select.Field;
import lombok.Getter;

@Getter
public class OrderBuilder {
		private Table table;
		private Field field;
		private OrderDirection direction;

		public OrderBuilder add(Table table, Field field, OrderDirection direction) {
				this.table = table;
				this.field = field;
				this.direction = direction;
				return this;
		}

		public Order build() {
				return new Order(table.getAlias() + "." + field.getName() + " " + direction.getValue());
		}

}
