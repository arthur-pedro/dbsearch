package com.dbsearch.api.core.database.pagination;

import lombok.Getter;

@Getter
public class PaginationBuilder {
		private Page page;

		public PaginationBuilder add(Page page) {
				this.page = page;
				return this;
		}

		public Pagination build() {
				return new Pagination(
								"LIMIT " + this.page.getLimit() + " OFFSET " + this.page.getOffset()
				);
		}
}
