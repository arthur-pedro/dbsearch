import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { provideIcons } from '@ng-icons/core';
import { lucideBox, lucideCross, lucideFolderSearch } from '@ng-icons/lucide';
import { HlmIconComponent } from '@spartan-ng/ui-icon-helm';
import { Observable, tap } from 'rxjs';
import { HlmPaginationContentDirective } from '../../../@core/components/ui-pagination-helm/src/lib/hlm-pagination-content.directive';
import { HlmPaginationEllipsisComponent } from '../../../@core/components/ui-pagination-helm/src/lib/hlm-pagination-ellipsis.componet';
import { HlmPaginationItemDirective } from '../../../@core/components/ui-pagination-helm/src/lib/hlm-pagination-item.directive';
import { HlmPaginationLinkDirective } from '../../../@core/components/ui-pagination-helm/src/lib/hlm-pagination-link.directive';
import { HlmPaginationNextComponent } from '../../../@core/components/ui-pagination-helm/src/lib/hlm-pagination-next.componet';
import { HlmPaginationPreviousComponent } from '../../../@core/components/ui-pagination-helm/src/lib/hlm-pagination-previous.componet';
import { HlmPaginationDirective } from '../../../@core/components/ui-pagination-helm/src/lib/hlm-pagination.directive';
import { HlmCaptionComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-caption.component';
import { HlmTableComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-table.component';
import { HlmTdComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-td.component';
import { HlmThComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-th.component';
import { HlmTrowComponent } from '../../../@core/components/ui-table-helm/src/lib/hlm-trow.component';
import { TableData } from '../../../@core/contracts/table/table-data.contract';
import { TableGateway } from '../../gateway/table-gateway.service';
import { SearchService, Where } from '../../services/search.service';

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [
    CommonModule,
    HlmCaptionComponent,
    HlmTableComponent,
    HlmTdComponent,
    HlmThComponent,
    HlmTrowComponent,
    HlmIconComponent,
    HlmPaginationDirective,
    HlmPaginationContentDirective,
    HlmPaginationItemDirective,
    HlmPaginationPreviousComponent,
    HlmPaginationNextComponent,
    HlmPaginationLinkDirective,
    HlmPaginationEllipsisComponent,
  ],
  providers: [provideIcons({ lucideBox, lucideFolderSearch, lucideCross })],
  host: {
    class: 'w-full overflow-x-auto',
  },
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
})
export class TableComponent implements OnDestroy, OnInit {
  protected table$: Observable<TableData> = new Observable();
  protected where: Where = new Map();
  constructor(
    private tableService: TableGateway,
    private snapshot: ActivatedRoute,
    private route: Router,
    private searchService: SearchService
  ) {
    this.snapshot.params.subscribe(({ table, schema }) => {
      this.snapshot.queryParamMap.subscribe((params) => {
        const page = params.get('page') || '1';
        const pageSize = params.get('pageSize') || '10';
        if (!page || !pageSize || !table || !schema) {
          this.route.navigate(['/']);
          return;
        }
        this.searchService.setPage(parseInt(page, 10));
        this.searchService.setPageSize(parseInt(pageSize, 10));
        this.searchService.setSchema(schema);
        this.searchService.setTable(table);
        this.searchService.where.subscribe((where: Where) => {
          this.table$ = this.fetchTableData(
            this.searchService.getSchema(),
            this.searchService.getTable(),
            this.searchService.getPage(),
            this.searchService.getPageSize(),
            where
          );
        });
      });
    });
  }

  fetchTableData(
    schema: string,
    table: string,
    page: number,
    pageSize: number,
    where: Where = new Map()
  ): Observable<TableData> {
    return this.tableService
      .fetchTableData(schema, table, where, {
        page,
        pageSize,
      })
      .pipe(
        tap((res: TableData) => {
          this.searchService.setPage(this.searchService.getPage());
          this.searchService.setPageSize(pageSize);
          this.searchService.setTable(table);
          this.searchService.setSchema(schema);
          this.searchService.setColumns(res.columns);
        })
      );
  }

  nextPage(): void {
    this.route.navigate([], {
      queryParams: {
        page: this.searchService.setPage(this.searchService.getPage() + 1),
      },
    });
  }

  previousPage(): void {
    if (this.searchService.getPage() <= 1) {
      return;
    }
    this.route.navigate([], {
      queryParams: {
        page: this.searchService.setPage(this.searchService.getPage() - 1),
      },
    });
  }

  ngOnInit(): void {
    this.searchService.clear();
  }

  ngOnDestroy(): void {
    this.searchService.where?.subscribe()?.unsubscribe();
    this.searchService.clear();
  }
}
