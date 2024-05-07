import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { provideIcons } from '@ng-icons/core';
import { lucideBell, lucideCheck, lucideGlobe } from '@ng-icons/lucide';
import { Observable } from 'rxjs';
import { HlmButtonDirective } from '../../../@core/components/ui-button-helm/src/lib/hlm-button.directive';
import { HlmCardContentDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-content.directive';
import { HlmCardDescriptionDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-description.directive';
import { HlmCardFooterDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-footer.directive';
import { HlmCardHeaderDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-header.directive';
import { HlmCardTitleDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-title.directive';
import { HlmCardDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card.directive';
import { HlmIconComponent } from '../../../@core/components/ui-icon-helm/src/lib/hlm-icon.component';
import { Connection } from '../../../@core/contracts/connection/connection.contract';
import { ConnectionGateway } from '../../gateway/connection-gateway.service';
import { StorageService } from '../../services/storage.service';
import { HlmBadgeDirective } from '../../../@core/components/ui-badge-helm/src/lib/hlm-badge.directive';

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
    HlmBadgeDirective,
  ],
  providers: [provideIcons({ lucideCheck, lucideBell, lucideGlobe })],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  protected connections$: Observable<Connection[]> =
    this.connectionService.list();

  constructor(
    private router: Router,
    private storageServive: StorageService,
    private connectionService: ConnectionGateway
  ) {}

  ngOnInit() {
    this.storageServive.clear();
  }

  goToConnection(connection: Connection) {
    this.storageServive.setConnection(connection);
    this.router.navigate(['connection', connection.id]);
  }
}
