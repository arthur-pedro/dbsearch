import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { ClarityIcons, userIcon } from '@cds/core/icon';
import { ClarityModule } from '@clr/angular';
import { StorageService } from '../../services/storage.service';
import { SearchService } from '../../services/search.service';
import { ExportGateway } from '../../gateway/export-gateway.service';
import { Filter } from '../../../@core/contracts/table/request/search.contract';
import { ConditionOperator } from '../../../@core/types/condition-operatoe.enum';
import { ReactiveFormsModule } from '@angular/forms';

ClarityIcons.addIcons(userIcon);

@Component({
  selector: 'app-search',
  standalone: true,
  imports: [CommonModule, ClarityModule, ReactiveFormsModule],
  templateUrl: './search.component.html',
  styleUrl: './search.component.css',
})
export class SearchComponent {
  constructor(
    public searchService: SearchService,
    public exportGateway: ExportGateway
  ) {}

  goBack(): void {
    // this.location.back();
  }

  onConditionOperationChange(filter: Filter | undefined, $event: Event): void {
    const selectElement = $event.target as HTMLSelectElement;
    const value = selectElement.value;
    if (filter) {
      this.searchService.setFilter({
        ...filter,
        conditionOperator: value as ConditionOperator,
      });
    }
  }

  onInputFilter(filter: Filter | undefined, $event: Event): void {
    const inputElement = $event.target as HTMLInputElement;
    let value: string | undefined = inputElement.value;
    if (value === '') {
      value = undefined;
    }
    if (filter) {
      this.searchService.setFilter({
        ...filter,
        value,
      });
    }
  }
}
