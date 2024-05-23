package com.dbsearch.api.core.database.query;

import com.dbsearch.api.core.database.from.From;
import com.dbsearch.api.core.database.from.FromBuilder;
import com.dbsearch.api.core.database.order.Order;
import com.dbsearch.api.core.database.order.OrderBuilder;
import com.dbsearch.api.core.database.pagination.Pagination;
import com.dbsearch.api.core.database.pagination.PaginationBuilder;
import com.dbsearch.api.core.database.select.Select;
import com.dbsearch.api.core.database.select.SelectBuilder;
import com.dbsearch.api.core.database.where.Where;
import com.dbsearch.api.core.database.where.WhereBuilder;

public class QueryBuilder {
		private Select select;
		private From from;
		private Where where;
		private Order order;
		private Pagination pagination;

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

		public QueryBuilder page(PaginationBuilder value) {
				this.pagination = value.build();
				return this;
		}

		public String build() {
				String DEFAULT_LIMIT = "10";
				String DEFAULT_SELECT = "SELECT %s FROM %s WHERE 1=1 %s";
				String DEFAULT_OFFSET = "0";
				return String.format(DEFAULT_SELECT,
								select.value(),
								from.value(),
								where.value())
								+ (order != null ? " ORDER BY " + order.value() : "") // TODO: Colocar build da string no Order
								+ (pagination != null ? " " + pagination.value() :
								" LIMIT " + DEFAULT_LIMIT + " OFFSET " + DEFAULT_OFFSET); // TODO: Colocar build da string no Pagination
		}
}
