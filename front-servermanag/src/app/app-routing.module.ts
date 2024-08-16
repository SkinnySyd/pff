import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProviderListComponent } from './components/provider-list/provider-list.component';
import { ServerListComponent } from './components/server-list/server-list.component';
import { AddProviderComponent } from './components/add-provider/add-provider.component';
import { AddServerComponent } from './components/add-server/add-server.component';

const routes: Routes = [
  { path: 'providers', component: ProviderListComponent },
  { path: 'servers', component: ServerListComponent },
  { path: 'servers/add', component: AddServerComponent },
  { path: 'providers/add', component: AddProviderComponent },
  { path: '', redirectTo: '/providers', pathMatch: 'full' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
