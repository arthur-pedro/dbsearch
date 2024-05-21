package com.dbsearch.api.core.database.pagination;

public class Page {
		private final int page;
		private final int size;

		public Page(int page, int size) {
				if (page < 1) {
						page = 1;
				}
				if (size < 1) {
						size = 10;
				}
				this.page = page;
				this.size = size;
		}

		public int getLimit() {
				return this.size;
		}

		public int getOffset() {
				return (this.page - 1) * this.size;
		}
}
