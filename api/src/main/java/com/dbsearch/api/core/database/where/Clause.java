package com.dbsearch.api.core.database.where;

import com.dbsearch.api.core.database.from.Table;
import com.dbsearch.api.core.database.select.Field;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Clause {
		private Table table;
		private Field field;
		private ClauseOperation operation;
		private String value;


		public String toString() {
				return table.getAlias() + "." + field.getName() + " " + operation.getValue() + " '" + value + "'";
		}
}
