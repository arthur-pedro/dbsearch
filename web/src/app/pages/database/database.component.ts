import { CommonModule } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { provideIcons } from '@ng-icons/core';
import { lucideBox, lucideNewspaper } from '@ng-icons/lucide';
import { Observable } from 'rxjs';
import { HlmButtonDirective } from '../../../@core/components/ui-button-helm/src/lib/hlm-button.directive';
import { HlmCaptionComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-caption.component';
import { HlmTableComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-table.component';
import { HlmTdComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-td.component';
import { HlmThComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-th.component';
import { HlmTrowComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-trow.component';
import { Schema } from '../../../@core/contracts/schema/schema.contract';
import { Table } from '../../../@core/contracts/table/table.contract';
import { DatabaseGateway } from '../../http/database-gateway.service';
import { TableGateway } from '../../http/table-gateway.service';
import { HlmIconComponent } from '@spartan-ng/ui-icon-helm';
import { HlmCardContentDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-content.directive';
import { HlmCardDescriptionDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-description.directive';
import { HlmCardFooterDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-footer.directive';
import { HlmCardHeaderDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-header.directive';
import { HlmCardTitleDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-title.directive';
import { HlmCardDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card.directive';

@Component({
  selector: 'app-database',
  standalone: true,
  imports: [
    CommonModule,
    HlmCaptionComponent,
    HlmTableComponent,
    HlmTdComponent,
    HlmThComponent,
    HlmTrowComponent,
    HlmButtonDirective,
    HlmButtonDirective,
    HlmCardContentDirective,
    HlmCardDescriptionDirective,
    HlmCardDirective,
    HlmCardFooterDirective,
    HlmCardHeaderDirective,
    HlmCardTitleDirective,
    HlmIconComponent,
  ],
  providers: [provideIcons({ lucideBox, lucideNewspaper })],
  templateUrl: './database.component.html',
  styleUrl: './database.component.css',
})
export class DatabaseComponent implements OnDestroy {
  protected schemas$: Observable<Schema[]> = new Observable();
  protected tables$: Observable<Table[]> = new Observable();
  constructor(
    private tableService: TableGateway,
    private dbService: DatabaseGateway,
    private snapshot: ActivatedRoute,
    private router: Router
  ) {
    this.snapshot.params.subscribe((params) => {
      const id = params['id'];
      if (!id) this.router.navigate(['/']);
      this.schemas$ = this.dbService.fetchSchemas(params['id']);
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
    this.router.navigate([`/schema/${schema.name}`]);
  }
}
