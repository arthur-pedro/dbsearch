import { Schema } from '../schema/schema.contract';

export interface Database {
  id: string;
  name: string;
  description: string;
  schemas: Schema[];
}

export class DatabaseModel implements Database {
  id: string;
  name: string;
  description: string;
  schemas: Schema[];

  constructor(
    id: string,
    name: string,
    schemas: Schema[],
    description: string
  ) {
    this.id = id;
    this.name = name;
    this.schemas = schemas;
    this.description = description;
  }
}
