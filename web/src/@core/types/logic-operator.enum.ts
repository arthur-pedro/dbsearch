export enum LogicOperator {
  AND = 'AND',
  OR = 'OR',
  NOT = 'NOT',
}

export const LOGIC_OPERATOR_SELECT_OPTIONS: {
  value: LogicOperator;
  label: string;
}[] = [
  { value: LogicOperator.AND, label: 'E' },
  { value: LogicOperator.OR, label: 'OU' },
  { value: LogicOperator.NOT, label: 'NÃO' },
];
