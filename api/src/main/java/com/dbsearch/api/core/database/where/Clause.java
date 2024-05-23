package com.dbsearch.api.core.database.where;

import com.dbsearch.api.core.database.column.Column;
import com.dbsearch.api.core.database.from.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Clause {
		private Table table;
		private Column column;
		private ClauseConditionOperator logicCondition;
		private ClauseLogicOperator logicOperator;
		private String value;


		public String toString() {
				if (logicCondition.equals(ClauseConditionOperator.IS_NULL) ||
								logicCondition.equals(ClauseConditionOperator.IS_NOT_NULL))
						return
										logicOperator.getValue()
														+ " "
														+ table.getAlias()
														+ "."
														+ column.getFormatedName()
														+ " "
														+ logicCondition.getValue();
				else if (logicCondition.equals(ClauseConditionOperator.IN) ||
								logicCondition.equals(ClauseConditionOperator.NOT_IN))
						return
										logicOperator.getValue()
														+ " "
														+ table.getAlias()
														+ "."
														+ column.getFormatedName()
														+ " "
														+ logicCondition.getValue()
														+ " ("
														+ value
														+ ")";
				else if (logicCondition.equals(ClauseConditionOperator.BETWEEN))
						return
										logicOperator.getValue()
														+ " "
														+ table.getAlias()
														+ "."
														+ column.getFormatedName()
														+ " "
														+ logicCondition.getValue()
														+ " "
														+ value;
				else if (logicCondition.equals(ClauseConditionOperator.LIKE))
						return
										logicOperator.getValue()
														+ " "
														+ table.getAlias()
														+ "."
														+ column.getFormatedName()
														+ " "
														+ logicCondition.getValue()
														+ " '%"
														+ value
														+ "%'";
				else if (logicCondition.equals(ClauseConditionOperator.EQUALS) ||
								logicCondition.equals(ClauseConditionOperator.NOT_EQUALS) ||
								logicCondition.equals(ClauseConditionOperator.GREATER_THAN) ||
								logicCondition.equals(ClauseConditionOperator.GREATER_THAN_OR_EQUALS) ||
								logicCondition.equals(ClauseConditionOperator.LESS_THAN) ||
								logicCondition.equals(ClauseConditionOperator.LESS_THAN_OR_EQUALS))

						return
										logicOperator.getValue()
														+ " "
														+ table.getAlias()
														+ "."
														+ column.getFormatedName()
														+ " "
														+ logicCondition.getValue()
														+ " '"
														+ value
														+ "'";
				else
						return "";
		}
}
