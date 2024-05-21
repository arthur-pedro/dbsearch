import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import {
  ClarityIcons,
  angleIcon,
  pencilIcon,
  plusIcon,
  refreshIcon,
  tableIcon,
} from '@cds/core/icon';
import { ClarityModule, ClrDatagridFilterInterface } from '@clr/angular';
import { Observable, Subject, finalize, tap } from 'rxjs';
import {
  Filter,
  TableSearch,
} from '../../../@core/contracts/table/request/search.contract';
import {
  TableData,
  TableDataUnique,
} from '../../../@core/contracts/table/response/table-data.contract';
import { CONDITION_OPERATOR_SELECT_OPTIONS } from '../../../@core/types/condition-operatoe.enum';
import { ExportGateway } from '../../gateway/export-gateway.service';
import { TableGateway } from '../../gateway/table-gateway.service';
import { FilterTableDataPipe } from '../../helpers/filter-table-data.pipe';
import { SearchService } from '../../services/search.service';

ClarityIcons.addIcons(tableIcon);
ClarityIcons.addIcons(refreshIcon);
ClarityIcons.addIcons(plusIcon);
ClarityIcons.addIcons(angleIcon);
ClarityIcons.addIcons(pencilIcon);

class MyFilter implements ClrDatagridFilterInterface<TableDataUnique> {
  changes = new Subject<any>();
  isActive(): boolean {
    return true;
  }
  accepts(dataUnique: TableDataUnique) {
    return true;
  }
}

@Component({
  selector: 'app-table',
  standalone: true,
  imports: [FilterTableDataPipe, CommonModule, ClarityModule],
  host: {
    class: 'w-full overflow-x-auto',
  },
  templateUrl: './table.component.html',
  styleUrl: './table.component.css',
})
export class TableComponent implements OnDestroy, OnInit {
  protected table$: Observable<TableData> = new Observable();
  protected filters: Filter[] = [];

  public selectedTable: string = '';
  public selectedRows: TableData[] = [];
  public searching: boolean = false;
  public conditionOperatorList: {
    label: string;
    value: string;
  }[] = CONDITION_OPERATOR_SELECT_OPTIONS;

  public myFilter = new MyFilter();

  constructor(
    private tableService: TableGateway,
    private snapshot: ActivatedRoute,
    private route: Router,
    public searchService: SearchService,
    private exportGateway: ExportGateway
  ) {
    this.snapshot.params.subscribe(({ table, schema }) => {
      this.snapshot.queryParamMap.subscribe((params) => {
        const page = params.get('page') || '1';
        const pageSize = params.get('pageSize') || '50';
        if (!page || !pageSize || !table || !schema) {
          this.route.navigate(['/']);
          return;
        }
        this.selectedTable = table;
        this.searchService.setPage(parseInt(page, 10));
        this.searchService.setPageSize(parseInt(pageSize, 10));
        this.searchService.setSchema(schema);
        this.searchService.setTable(table);
        this.searchService.filters$.subscribe((filter: Map<string, Filter>) => {
          this.table$ = this.fetchTableData(
            this.searchService.getSchema(),
            this.searchService.getTable(),
            this.searchService.getPage(),
            this.searchService.getPageSize(),
            Array.from(filter.values())
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
    filters: Filter[] = []
  ): Observable<TableData> {
    const search = TableSearch.fromJSON({
      columns: [],
      filters,
      page: page,
      pageSize: pageSize,
      schema,
      tableName: table,
    });
    this.searching = true;
    return this.tableService.fetchTableData(search).pipe(
      tap((res: TableData) => {
        this.searchService.setPage(res.page);
        this.searchService.setPageSize(res.size);
        this.searchService.setTable(res.tableName);
        this.searchService.setSchema(schema);
        this.searchService.setColumns(res.columns);
        this.searchService.setTotalData(res.totalData);
      }),
      finalize(() => {
        this.searching = false;
      })
    );
  }

  ngOnInit(): void {
    this.searchService.clear();
  }

  ngOnDestroy(): void {
    this.searchService.filters$?.subscribe()?.unsubscribe();
    this.searchService.clear();
  }

  public onAdd(): void {
    console.log('Add');
  }

  public onEdit(): void {
    console.log('Edit');
  }

  public onDelete(): void {
    console.log('Delete');
  }

  public onRefresh(): void {
    console.log('Refresh');
  }

  public onExportAll(): void {
    this.exportGateway
      .export(this.searchService.getPayload())
      .subscribe((response) => {
        const blob = new Blob([response], { type: 'application/octet-stream' });
        this.downloadCSV(blob);
      });
  }

  public onExportSelected(): void {
    console.log('Export Selected');
  }

  downloadCSV(blob: Blob) {
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    a.href = url;
    a.download = 'exported_data.csv';
    a.click();
    window.URL.revokeObjectURL(url);
  }

  onPageChange(page: number): void {
    this.route.navigate([], {
      queryParams: {
        page: page,
        pageSize: this.searchService.getPageSize(),
      },
    });
    this.table$ = this.fetchTableData(
      this.searchService.getSchema(),
      this.searchService.getTable(),
      page,
      this.searchService.getPageSize(),
      this.filters
    );
  }
}
