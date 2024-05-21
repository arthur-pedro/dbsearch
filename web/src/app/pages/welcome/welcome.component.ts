import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterOutlet } from '@angular/router';
import { ClarityIcons, userIcon } from '@cds/core/icon';
import { ClarityModule } from '@clr/angular';

ClarityIcons.addIcons(userIcon);

@Component({
  selector: 'app-welcome',
  standalone: true,
  imports: [RouterOutlet, CommonModule, ClarityModule, ReactiveFormsModule],
  templateUrl: './welcome.component.html',
  styleUrl: './welcome.component.css',
})
export class WelcomeComponent {
  constructor() {}
}
