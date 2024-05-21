package com.dbsearch.api.core.dto;

import com.dbsearch.api.core.database.where.ClauseConditionOperator;
import com.dbsearch.api.core.database.where.ClauseLogicOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FilterDTO {
		private String column;
		private String value;
		private ClauseLogicOperator logicOperator;
		private ClauseConditionOperator conditionOperator;
}
