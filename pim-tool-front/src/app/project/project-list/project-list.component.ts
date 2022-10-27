import {Component, OnInit} from '@angular/core';
import {Project} from "../project.model";
import {ProjectService} from "../project.service";
import {FormControl, FormGroup} from "@angular/forms";
import {LocalService} from "../../shared/local.service";

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projectList: Project[] = []
  status: string[] = ['New', 'Planned', 'In progress', 'Finished'];
  searchForm: FormGroup;
  isNotFound = false;
  listCheckedProjectId: number[] = [];
  isDeletedListItem = false;

  searchControls = {
    'projectInfo': new FormControl(''),
    'status': new FormControl('')
  }

  constructor(private projectService: ProjectService,
              private localStore: LocalService) {
  }

  ngOnInit(): void {
    this.initSearchForm();

    if (this.searchForm.value.projectInfo !== '' || this.searchForm.value.status !== '') {
      console.log(this.searchForm.value);
      this.onSubmit();
    } else {
      this.getAllProject();
    }
  }

  private initSearchForm() {
    this.searchForm = new FormGroup(this.searchControls);

    if (this.localStore.getData('searchData') !== null) {
      this.searchForm.patchValue({
        'projectInfo': this.localStore.getData('searchData').projectInfo || '',
        'status': this.localStore.getData('searchData').projectStatus || ''
      })
    }
  }

  private getAllProject() {
    this.projectService.fetchProjects().subscribe(
      projects => {
        this.projectList = projects;
      }
    );
  }

  onSubmit() {
    let data = this.searchForm.value;
    if (data.projectInfo !== '' || data.status !== '') {
      this.localStore.saveData("searchData", {projectInfo: data.projectInfo, projectStatus: data.status});
      this.projectService.searchProject(data.projectInfo, data.status).subscribe(res => {
        if (res.length !== 0) {
          this.isNotFound = false;
          this.projectList = res;
        } else {
          this.isNotFound = true;
        }
      });
    } else {
      this.isNotFound = false;
      this.localStore.clearData();
      this.getAllProject();
    }
  }

  onReset() {
    this.localStore.clearData();
    this.searchForm.setValue({
      'projectInfo': '',
      'status': ''
    });
    this.isNotFound = false;
    this.getAllProject();
  }

  onDelete(projectId: number) {
    if (confirm("Are you sure to delete this project?")) {
      this.projectService.deleteProject(projectId).subscribe(res => {
        console.log(res);
        this.getAllProject();
      });
    }
  }

  isDisabled(status: string) {
    return status !== 'New' ? true : null
  }

  onChecked(event: any, id: number) {
    if (event.target.checked) {
      this.listCheckedProjectId.push(id);
    } else {
      this.listCheckedProjectId = this.listCheckedProjectId.filter(projectId => projectId !== id);
    }

    if (this.listCheckedProjectId.length > 1) {
      this.isDeletedListItem = true;
    } else {
      this.isDeletedListItem = false;
    }
  }

  onDeleteListProject() {
    if (confirm("Are you sure to delete all selected projects?")) {
      this.projectService.deleteListProject(this.listCheckedProjectId).subscribe(res => {
        console.log(res);
        this.listCheckedProjectId = [];
        this.getAllProject();
      });
    }
  }
}
