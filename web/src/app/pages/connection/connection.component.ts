import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ClarityModule } from '@clr/angular';
import { Observable } from 'rxjs';
import { Database } from '../../../@core/contracts/database/database.contract';
import { ConnectionGateway } from '../../gateway/connection-gateway.service';
import { ClarityIcons, dataClusterIcon } from '@cds/core/icon';

ClarityIcons.addIcons(dataClusterIcon);

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ClarityModule],
  templateUrl: './connection.component.html',
  styleUrl: './connection.component.css',
})
export class ConnectionComponent {
  protected databases$: Observable<Database[]> = new Observable();
  private connectionId: string = '';

  constructor(
    private router: Router,
    private connectionService: ConnectionGateway,
    private snapshot: ActivatedRoute
  ) {
    this.snapshot.params.subscribe((params) => {
      if (!params['id']) this.router.navigate(['/']);
      this.connectionId = params['id'];
      this.databases$ = this.connectionService.fetchDatabases();
    });
  }

  goToDatabase(db: Database) {
    this.router.navigate([
      `dashboard/connection/${this.connectionId}/database`,
      db.id,
    ]);
  }
}
