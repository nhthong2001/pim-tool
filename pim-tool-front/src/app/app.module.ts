import { NgModule } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { BrowserModule } from '@angular/platform-browser';
import { TranslateModule, TranslateLoader } from '@ngx-translate/core'
import {  TranslateHttpLoader } from '@ngx-translate/http-loader'

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { SidebarComponent } from './sidebar/sidebar.component';
import { ProjectComponent } from './project/project.component';
import { ProjectFormComponent } from './project/project-form/project-form.component';
import { ProjectListComponent } from './project/project-list/project-list.component';
import { ErrorPageComponent } from './error-page/error-page.component';
import {AppRoutingModule} from "./app-routing.module";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    SidebarComponent,
    ProjectComponent,
    ProjectFormComponent,
    ProjectListComponent,
    ErrorPageComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: httpTranslateLoader,
        deps: [HttpClient]
      }
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }

export  function httpTranslateLoader(http: HttpClient) {
  return new TranslateHttpLoader(http, "assets/i18n/", ".json");
}
