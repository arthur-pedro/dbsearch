import {Pipe, PipeTransform} from '@angular/core';
import {TableDataList} from "../../@core/contracts/table/table-data.contract";

@Pipe({
  name: 'filter',
  standalone: true,
})
export class FilterPipe implements PipeTransform {
  transform(items: TableDataList, filterBy: string): TableDataList {
    if (!items || !filterBy) {
      return items;
    }
    return items.filter(item => {
      return Object.keys(item).some(key => {
          return String(item[key]).toLowerCase().includes(filterBy.toLowerCase());
        }
      );
    });
  }
}
