import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Connection } from '../../@core/contracts/connection/connection.contract';
import { Database } from '../../@core/contracts/database/database.contract';
import { environment } from '../../environments/environment';

@Injectable({ providedIn: 'root' })
export class ConnectionGateway {
  constructor(private readonly http: HttpClient) {}

  list(): Observable<Connection[]> {
    return this.http.get<Connection[]>(environment.apiUrl + `/connection`);
  }

  fetchDatabases(): Observable<Database[]> {
    return this.http.get<Database[]>(
      environment.apiUrl + `/connection/databases`
    );
  }
}
