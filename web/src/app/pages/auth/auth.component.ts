import { CommonModule, Location } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterOutlet } from '@angular/router';
import {
  ClarityModule,
  ClrButtonModule,
  ClrCheckboxModule,
  ClrDropdownModule,
  ClrFormsModule,
  ClrInputModule,
  ClrPasswordModule,
  ClrSelectModule,
} from '@clr/angular';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterOutlet,
    // Clarity
    ClarityModule,
    ClrButtonModule,
    ClrInputModule,
    ClrFormsModule,
    ClrDropdownModule,
    ClrSelectModule,
    ClrPasswordModule,
    ClrCheckboxModule,
  ],
  providers: [],
  templateUrl: './auth.component.html',
})
export class AuthComponent {
  title = 'Db Search';

  constructor(private router: Router) {}

  goToHome(): void {
    this.router.navigate(['dashboard/welcome']);
  }
}
