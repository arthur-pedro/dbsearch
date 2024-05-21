import { Pipe, PipeTransform } from '@angular/core';
import { TableDataList } from '../../@core/contracts/table/response/table-data.contract';

@Pipe({
  name: 'filterTableData',
  standalone: true,
})
export class FilterTableDataPipe implements PipeTransform {
  transform(items: TableDataList, filterBy: string): TableDataList {
    if (!items || !filterBy) {
      return items;
    }
    return items.filter((item) => {
      return Object.keys(item).some((key) => {
        return String(item[key]).toLowerCase().includes(filterBy.toLowerCase());
      });
    });
  }
}
