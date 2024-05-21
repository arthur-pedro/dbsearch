import { CommonModule } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClarityIcons, tableIcon } from '@cds/core/icon';
import { Observable } from 'rxjs';
import { Schema } from '../../../@core/contracts/schema/schema.contract';
import { Table } from '../../../@core/contracts/table/response/table.contract';
import { DatabaseGateway } from '../../gateway/database-gateway.service';
import { TableGateway } from '../../gateway/table-gateway.service';
import { ClarityModule } from '@clr/angular';

ClarityIcons.addIcons(tableIcon);
@Component({
  selector: 'app-database',
  standalone: true,
  imports: [CommonModule, ClarityModule],
  templateUrl: './database.component.html',
  styleUrl: './database.component.css',
})
export class DatabaseComponent implements OnDestroy {
  protected schemas$: Observable<Schema[]> = new Observable();
  protected tables$: Observable<Table[]> = new Observable();
  private connectionId: string = '';
  constructor(
    private tableService: TableGateway,
    private dbService: DatabaseGateway,
    private snapshot: ActivatedRoute,
    private router: Router
  ) {
    this.snapshot.params.subscribe((params) => {
      const id = params['id'];
      const connectionId = params['connectionId'];
      if (!id) this.router.navigate(['/']);
      if (!connectionId) this.router.navigate(['/']);
      this.connectionId = connectionId;
      this.schemas$ = this.dbService.fetchSchemas(id);
    });
  }

  ngOnDestroy(): void {
    this.schemas$.subscribe().unsubscribe();
    this.tables$.subscribe().unsubscribe();
  }

  fetchTables(schema: string) {
    this.tables$ = this.tableService.list(schema);
  }

  goToSchema(schema: Schema) {
    this.router.navigate([
      `dashboard/connection/${this.connectionId}/schema/${schema.name}`,
    ]);
  }
}
