import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";

import {ErrorPageComponent} from "./error-page/error-page.component";
import {ProjectComponent} from "./project/project.component";
import {ProjectFormComponent} from "./project/project-form/project-form.component";
import {ProjectListComponent} from "./project/project-list/project-list.component";

const appRoutes: Routes = [
  {path: '', redirectTo: '/project/project-list', pathMatch: "full"},
  {
    path: 'project', component: ProjectComponent, children: [
      {path: 'project-list', component: ProjectListComponent},
      {path: 'new', component: ProjectFormComponent},
      {path: 'update/:id', component: ProjectFormComponent},
    ]
  },
  {path: '**', component: ErrorPageComponent},
];

@NgModule({
    imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
  }
)
export class AppRoutingModule {

}
