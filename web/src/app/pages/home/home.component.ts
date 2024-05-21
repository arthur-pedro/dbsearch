import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { ClarityIcons, userIcon, networkGlobeIcon } from '@cds/core/icon';
import { ClarityModule } from '@clr/angular';
import { Observable } from 'rxjs';
import { Connection } from '../../../@core/contracts/connection/connection.contract';
import { ConnectionGateway } from '../../gateway/connection-gateway.service';
import { StorageService } from '../../services/storage.service';

ClarityIcons.addIcons(userIcon);
ClarityIcons.addIcons(networkGlobeIcon);
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, ClarityModule, ReactiveFormsModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent {}
