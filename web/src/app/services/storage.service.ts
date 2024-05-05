import { Injectable } from '@angular/core';
import {
  Connection,
  ConnectionModel,
} from '../../@core/contracts/connection/connection.contract';

@Injectable({ providedIn: 'root' })
export class StorageService {
  setConnection(connection: Connection): void {
    localStorage.setItem('connection', JSON.stringify(connection));
  }

  getConnection(): Connection | null {
    const connection = localStorage.getItem('connection');
    if (!connection) return null;
    const parsedConnection = JSON.parse(connection);
    return new ConnectionModel(
      parsedConnection.id,
      parsedConnection.name,
      parsedConnection.description,
      parsedConnection.host,
      parsedConnection.driver,
      []
    );
  }

  clear(): void {
    localStorage.clear();
  }
}
