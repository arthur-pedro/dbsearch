import { Database } from '../database/database.contract';

export interface Connection {
  id: string;
  name: string;
  description: string;
  host: string;
  driver: string;
  databases: Database[];
}

export class ConnectionModel implements Connection {
  id: string;
  name: string;
  description: string;
  databases: Database[];
  host: string;
  driver: string;

  constructor(
    id: string,
    name: string,
    description: string,
    host: string,
    driver: string,
    databases: Database[]
  ) {
    this.id = id;
    this.name = name;
    this.description = description;
    this.databases = databases;
    this.host = host;
    this.driver = driver;
  }
}
