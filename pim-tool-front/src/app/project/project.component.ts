import {AfterViewInit, Component, OnInit} from '@angular/core';
import {ProjectService} from "./project.service";
import {HttpClient} from "@angular/common/http";
import {Project} from "./project.model";

@Component({
  selector: 'app-project',
  templateUrl: './project.component.html',
  styleUrls: ['./project.component.css']
})
export class ProjectComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {

  }


}
