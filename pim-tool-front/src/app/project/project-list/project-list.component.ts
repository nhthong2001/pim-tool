import {Component, OnInit} from '@angular/core';
import {Project} from "../project.model";
import {ProjectService} from "../project.service";
import {FormControl, FormGroup} from "@angular/forms";
import {StoreService} from "../../shared/store.service";
import {ProjectStatus} from "../project-status.enum";

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {

  projectList: Project[] = []
  status: ProjectStatus[] = [ProjectStatus.NEW, ProjectStatus.PLA, ProjectStatus.INP, ProjectStatus.FIN];
  searchForm: FormGroup;
  isNotFound = false;
  listCheckedProjectId: number[] = [];
  isDeletedListItem = false;
  ProjectStatus = ProjectStatus;

  searchControls = {
    'projectInfo': new FormControl(''),
    'status': new FormControl('')
  }

  constructor(private projectService: ProjectService,
              private localStore: StoreService) {
  }

  ngOnInit(): void {
    this.initSearchForm();
  }

  private initSearchForm() {
    this.searchForm = new FormGroup(this.searchControls);
    if (this.localStore.searchInfo !== '' || this.localStore.searchStatus !== null) {
      console.log(this.localStore.searchInfo);
      console.log(this.localStore.searchStatus);

      this.searchForm.setValue({
        projectInfo: this.localStore.searchInfo,
        status: this.localStore.searchStatus === null ? '' : this.localStore.searchStatus
      });
      this.onSubmit();
    } else {
      this.searchForm = new FormGroup(this.searchControls);
      this.getAllProject();
    }
  }

  private getAllProject() {
    this.projectService.fetchProjects().subscribe(
      projects => {
        this.projectList = projects.map(p => Object.assign(new Project(), {...p, status: ProjectStatus[p.status]}));
        if (this.projectList.length === 0) {
          this.isNotFound = true;
        }
      }
    );
  }

  onSubmit() {
    let data = this.searchForm.value;
    if (data.projectInfo !== '' || data.status !== '') {
      let status: ProjectStatus = data.status === '' ? null: data.status;
      this.localStore.saveData(data.projectInfo, status);
      this.projectService.searchProject(data.projectInfo, ProjectStatus[data.status]).subscribe(res => {
        if (res.length !== 0) {
          this.isNotFound = false;
          this.projectList = res.map(p => Object.assign(new Project(), {...p, status: ProjectStatus[p.status]}));
        } else {
          this.isNotFound = true;
        }
      });
    } else {
      this.onReset();
    }
  }

  onReset() {
    this.localStore.resetData();
    this.searchForm.setValue({
      projectInfo: '',
      status: ''
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

  isDisabled(status: ProjectStatus) {
    return status !== ProjectStatus.NEW;
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

  projectStatusEnumToKey(status: ProjectStatus) {
    return ProjectStatus[status];
  }
}
