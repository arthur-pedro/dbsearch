import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { provideIcons } from '@ng-icons/core';
import { lucideBell, lucideCheck, lucideDatabase } from '@ng-icons/lucide';
import { Observable } from 'rxjs';
import { HlmButtonDirective } from '../../../@core/components/ui-button-helm/src/lib/hlm-button.directive';
import { HlmCardContentDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-content.directive';
import { HlmCardDescriptionDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-description.directive';
import { HlmCardFooterDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-footer.directive';
import { HlmCardHeaderDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-header.directive';
import { HlmCardTitleDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-title.directive';
import { HlmCardDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card.directive';
import { HlmIconComponent } from '../../../@core/components/ui-icon-helm/src/lib/hlm-icon.component';
import { Database } from '../../../@core/contracts/database/database.contract';
import { ConnectionGateway } from '../../http/connection-gateway.service';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    HlmButtonDirective,
    HlmCardContentDirective,
    HlmCardDescriptionDirective,
    HlmCardDirective,
    HlmCardFooterDirective,
    HlmCardHeaderDirective,
    HlmCardTitleDirective,
    HlmIconComponent,
  ],
  providers: [provideIcons({ lucideCheck, lucideBell, lucideDatabase })],
  templateUrl: './connection.component.html',
  styleUrl: './connection.component.css',
})
export class ConnectionComponent {
  protected databases$: Observable<Database[]> = new Observable();

  constructor(
    private router: Router,
    private connectionService: ConnectionGateway,
    private snapshot: ActivatedRoute
  ) {
    this.snapshot.params.subscribe((params) => {
      console.log(params);
      if (!params['id']) this.router.navigate(['/']);
      this.databases$ = this.connectionService.fetchDatabases();
    });
  }

  goToDatabase(db: Database) {
    this.router.navigate(['database', db.id]);
  }
}
