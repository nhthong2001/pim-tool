import {Component, OnInit} from '@angular/core';
import {Project} from "../project.model";
import {ProjectService} from "../project.service";
import {FormControl, FormGroup} from "@angular/forms";
import {StoreService} from "../../shared/store.service";
import {ProjectStatus} from "../project-status.enum";
import {TranslateService} from "@ngx-translate/core";

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
  ProjectStatus = ProjectStatus;

  searchControls = {
    'projectInfo': new FormControl(''),
    'status': new FormControl('')
  }

  constructor(private projectService: ProjectService,
              private localStore: StoreService,
              private translate: TranslateService) {
  }

  ngOnInit(): void {
    this.initSearchForm();
  }

  private initSearchForm() {
    this.searchForm = new FormGroup(this.searchControls);
    if (this.localStore.searchInfo !== '' || this.localStore.searchStatus !== null) {
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
      }, error => {
        this.isNotFound = true;
      }
    );
  }

  onSubmit() {
    let data = this.searchForm.value;
    if (data.projectInfo !== '' || data.status !== '') {
      let status: ProjectStatus = data.status === '' ? null : data.status;
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
    if (confirm(this.translate.instant("confirm_delete_item"))) {
      this.listCheckedProjectId.splice(this.listCheckedProjectId.indexOf(projectId), 1);
      console.log(this.listCheckedProjectId);
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
  }

  onDeleteListProject() {
    if (confirm(this.translate.instant("confirm_delete_list"))) {
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

  isChecked(projectId: number) {
    if (this.listCheckedProjectId.indexOf(projectId) !== -1) {
      return true;
    }
    return false;
  }
}
