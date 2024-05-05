import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Schema } from '../../@core/contracts/schema/schema.contract';
import { environment } from '../../environments/environment';
import { Database } from '../../@core/contracts/database/database.contract';

@Injectable({ providedIn: 'root' })
export class DatabaseGateway {
  constructor(private readonly http: HttpClient) {}

  list(): Observable<Database[]> {
    return this.http.get<Database[]>(environment.apiUrl + `/database`);
  }

  fetchSchemas(database: string): Observable<Schema[]> {
    return this.http.get<Schema[]>(
      environment.apiUrl + `/database/${database}/schemas`
    );
  }
}
