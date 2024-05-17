package com.dbsearch.api.core.database.query;

import com.dbsearch.api.core.database.from.From;
import com.dbsearch.api.core.database.from.FromBuilder;
import com.dbsearch.api.core.database.order.Order;
import com.dbsearch.api.core.database.order.OrderBuilder;
import com.dbsearch.api.core.database.select.Select;
import com.dbsearch.api.core.database.select.SelectBuilder;
import com.dbsearch.api.core.database.where.Where;
import com.dbsearch.api.core.database.where.WhereBuilder;

public class QueryBuilder {
		private Select select;
		private From from;
		private Where where;
		private Order order;

		public QueryBuilder() {
		}

		public QueryBuilder select(SelectBuilder value) {
				this.select = value.build();
				return this;
		}

		public QueryBuilder from(FromBuilder value) {
				this.from = value.build();
				return this;
		}

		public QueryBuilder where(WhereBuilder value) {
				this.where = value.build();
				return this;
		}

		public QueryBuilder order(OrderBuilder value) {
				this.order = value.build();
				return this;
		}

		public String build() {
				return String.format("SELECT %s FROM %s WHERE 1=1 AND %s",
								select.value(),
								from.value(),
								where.value())
								+ (order != null ? " ORDER BY " + order.value() : "");
		}
}
