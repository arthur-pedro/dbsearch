package com.dbsearch.api.core.database.join;

import com.dbsearch.api.core.database.column.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class JoinKey {
		private JoinType joinType;
		private Column column;
}
