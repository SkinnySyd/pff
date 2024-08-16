import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http'; 

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ProviderListComponent } from './components/provider-list/provider-list.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ServerListComponent } from './components/server-list/server-list.component';
import { AddServerComponent } from './components/add-server/add-server.component';
import { AddProviderComponent } from './components/add-provider/add-provider.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';



@NgModule({
  declarations: [
    AppComponent,
    ProviderListComponent,
    ServerListComponent,
    AddServerComponent,
    AddProviderComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule, 
    FormsModule,
    AppRoutingModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
