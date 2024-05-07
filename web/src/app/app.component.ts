import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { provideIcons } from '@ng-icons/core';
import {
  lucideBox,
  lucideArrowLeft,
  lucideSearch,
  lucideCross,
  lucideHome,
  lucideShare,
  lucideShare2
} from '@ng-icons/lucide';
import { HlmAlertDescriptionDirective } from '../@core/components/ui-alert-helm/src/lib/hlm-alert-description.directive';
import { HlmAlertIconDirective } from '../@core/components/ui-alert-helm/src/lib/hlm-alert-icon.directive';
import { HlmAlertTitleDirective } from '../@core/components/ui-alert-helm/src/lib/hlm-alert-title.directive';
import { HlmAlertDirective } from '../@core/components/ui-alert-helm/src/lib/hlm-alert.directive';
import { HlmIconComponent } from '../@core/components/ui-icon-helm/src/lib/hlm-icon.component';
import { CommonModule, Location } from '@angular/common';
import { HlmButtonDirective } from '../@core/components/ui-button-helm/src/lib/hlm-button.directive';
import {
  BrnSheetTriggerDirective,
  BrnSheetContentDirective,
} from '@spartan-ng/ui-sheet-brain';
import { HlmInputDirective } from '../@core/components/ui-input-helm/src/lib/hlm-input.directive';
import { HlmLabelDirective } from '../@core/components/ui-label-helm/src/lib/hlm-label.directive';
import { HlmSheetContentComponent } from '../@core/components/ui-sheet-helm/src/lib/hlm-sheet-content.component';
import { HlmSheetDescriptionDirective } from '../@core/components/ui-sheet-helm/src/lib/hlm-sheet-description.directive';
import { HlmSheetFooterComponent } from '../@core/components/ui-sheet-helm/src/lib/hlm-sheet-footer.component';
import { HlmSheetHeaderComponent } from '../@core/components/ui-sheet-helm/src/lib/hlm-sheet-header.component';
import { HlmSheetTitleDirective } from '../@core/components/ui-sheet-helm/src/lib/hlm-sheet-title.directive';
import { HlmSheetComponent } from '../@core/components/ui-sheet-helm/src/lib/hlm-sheet.component';
import { SearchService } from './services/search.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    RouterOutlet,
    HlmAlertDirective,
    HlmAlertDescriptionDirective,
    HlmAlertIconDirective,
    HlmAlertTitleDirective,
    HlmIconComponent,
    BrnSheetTriggerDirective,
    BrnSheetContentDirective,
    HlmSheetComponent,
    HlmSheetContentComponent,
    HlmSheetHeaderComponent,
    HlmSheetFooterComponent,
    HlmSheetTitleDirective,
    HlmSheetDescriptionDirective,
    HlmButtonDirective,
    HlmInputDirective,
    HlmIconComponent,
    HlmLabelDirective,
  ],
  providers: [
    provideIcons({ lucideBox, lucideArrowLeft, lucideSearch, lucideCross, lucideHome, lucideShare }),
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  constructor(
    private location: Location,
    public searchService: SearchService
  ) {}
  title = 'db-search';

  goBack(): void {
    this.location.back();
  }

  onInputFilter(column: string, $event: Event): void {
    const inputElement = $event.target as HTMLInputElement;
    let value: string | undefined = inputElement.value;
    if (value === '') {
      value = undefined;
    }
    this.searchService.setFilter(column, value);
  }
}
