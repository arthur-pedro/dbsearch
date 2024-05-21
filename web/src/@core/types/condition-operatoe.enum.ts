export enum ConditionOperator {
  GREATER_THAN = 'GREATER_THAN',
  LESS_THAN = 'LESS_THAN',
  GREATER_THAN_OR_EQUALS = 'GREATER_THAN_OR_EQUALS',
  LESS_THAN_OR_EQUALS = 'LESS_THAN_OR_EQUALS',
  NOT_EQUALS = 'NOT_EQUALS',
  LIKE = 'LIKE',
  IS_NULL = 'IS_NULL',
  IS_NOT_NULL = 'IS_NOT_NULL',
  EQUALS = 'EQUALS',
  ILIKE = 'ILIKE',
}

export const CONDITION_OPERATOR_SELECT_OPTIONS: {
  value: ConditionOperator;
  label: string;
}[] = [
  { value: ConditionOperator.LIKE, label: 'Contém' },
  { value: ConditionOperator.ILIKE, label: 'Contém (case-insensitive)' },
  { value: ConditionOperator.IS_NULL, label: 'É nulo' },
  { value: ConditionOperator.IS_NOT_NULL, label: 'Não é nulo' },
  { value: ConditionOperator.EQUALS, label: '=' },
  { value: ConditionOperator.NOT_EQUALS, label: '!=' },
  { value: ConditionOperator.GREATER_THAN, label: '>' },
  { value: ConditionOperator.LESS_THAN, label: '<' },
  { value: ConditionOperator.GREATER_THAN_OR_EQUALS, label: '>=' },
  { value: ConditionOperator.LESS_THAN_OR_EQUALS, label: '<=' },
];
