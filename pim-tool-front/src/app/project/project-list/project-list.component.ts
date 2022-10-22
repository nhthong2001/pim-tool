import { Component, OnInit } from '@angular/core';
import {Project} from "../project.model";
import {ProjectService} from "../project.service";

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projectList: Project[] = []

  constructor(private projectService: ProjectService) { }

  ngOnInit(): void {
    this.projectList = this.projectService.getProjects();
    console.log(this.projectList);
  }

}
