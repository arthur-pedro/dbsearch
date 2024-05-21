import { CommonModule } from '@angular/common';
import { Component, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { provideIcons } from '@ng-icons/core';
import { lucideBox, lucideTable } from '@ng-icons/lucide';
import { Observable } from 'rxjs';
import { HlmButtonDirective } from '../../../@core/components/ui-button-helm/src/lib/hlm-button.directive';
import { HlmCaptionComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-caption.component';
import { HlmTableComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-table.component';
import { HlmTdComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-td.component';
import { HlmThComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-th.component';
import { HlmTrowComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-trow.component';
import { TableGateway } from '../../gateway/table-gateway.service';
import { HlmIconComponent } from '@spartan-ng/ui-icon-helm';
import { HlmCardContentDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-content.directive';
import { HlmCardDescriptionDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-description.directive';
import { HlmCardFooterDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-footer.directive';
import { HlmCardHeaderDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-header.directive';
import { HlmCardTitleDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card-title.directive';
import { HlmCardDirective } from '../../../@core/components/ui-card-helm/src/lib/hlm-card.directive';
import { Table } from '../../../@core/contracts/table/response/table.contract';
import { ClarityModule } from '@clr/angular';
import { ClarityIcons, tableIcon } from '@cds/core/icon';

ClarityIcons.addIcons(tableIcon);

@Component({
  selector: 'app-schema',
  standalone: true,
  imports: [CommonModule, ClarityModule],
  templateUrl: './schema.component.html',
  styleUrl: './schema.component.css',
})
export class SchemaComponent implements OnDestroy {
  protected tables$: Observable<Table[]> = new Observable();
  private schemaName: string = '';
  private connectionId: string = '';
  constructor(
    private tableService: TableGateway,
    private snapshot: ActivatedRoute,
    private router: Router
  ) {
    this.snapshot.params.subscribe((params) => {
      const id = params['id'];
      const connectionId = params['connectionId'];
      this.schemaName = id;
      this.connectionId = connectionId;
      if (!id) this.router.navigate(['/']);
      if (!connectionId) this.router.navigate(['/']);
      this.tables$ = this.tableService.list(params['id']);
    });
  }

  ngOnDestroy(): void {
    this.tables$.subscribe().unsubscribe();
  }

  goToTable(table: Table) {
    this.router.navigate(
      [
        `dashboard/connection/${this.connectionId}/schema/${this.schemaName}/table/${table.tableName}`,
      ],
      { queryParams: { page: '1', pageSize: '50' } }
    );
  }
}
