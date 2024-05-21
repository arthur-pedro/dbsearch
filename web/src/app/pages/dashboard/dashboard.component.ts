import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterLinkActive, RouterModule, RouterOutlet } from '@angular/router';
import {
  ClarityIcons,
  cogIcon,
  connectIcon,
  homeIcon,
  libraryIcon,
  searchIcon,
  userIcon,
  vmBugIcon,
} from '@cds/core/icon';
import { ClarityModule } from '@clr/angular';
import { Observable } from 'rxjs';
import { Connection } from '../../../@core/contracts/connection/connection.contract';
import { ConnectionGateway } from '../../gateway/connection-gateway.service';
import { StorageService } from '../../services/storage.service';

ClarityIcons.addIcons(userIcon);
ClarityIcons.addIcons(searchIcon);
ClarityIcons.addIcons(cogIcon);
ClarityIcons.addIcons(homeIcon);
ClarityIcons.addIcons(vmBugIcon);
ClarityIcons.addIcons(connectIcon);
ClarityIcons.addIcons(libraryIcon);

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    RouterOutlet,
    ClarityModule,
    FormsModule,
    RouterModule,
    CommonModule,
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  public activeRouterConfig = {
    exact: false,
  };
  public collapsed = false;
  protected connections$: Observable<Connection[]> =
    this.connectionService.list();

  constructor(
    private storageServive: StorageService,
    private connectionService: ConnectionGateway
  ) {}

  goToConnection(connection: Connection) {
    this.storageServive.setConnection(connection);
  }
}
