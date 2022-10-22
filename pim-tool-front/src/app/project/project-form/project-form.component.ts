import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute} from "@angular/router";
import * as moment from 'moment';

import {GroupService} from "../../group/group.service";
import {ProjectService} from "../project.service";
import {Project} from "../project.model";
import {Group} from "../../group/group.model";

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.css']
})
export class ProjectFormComponent implements OnInit {
  project: Project;
  state = 'New Project';
  isEdited = false;
  isSubmitted = false;
  id: number;
  status: string[] = ['New', 'Planned', 'In progress', 'Finished'];
  defaultStatus: string = 'New';
  groups: Group[] = [];
  projectForm: FormGroup;

  constructor(private projectService: ProjectService, private groupService: GroupService, private route: ActivatedRoute) {
    this.project = new Project();
  }

  ngOnInit(): void {
    this.isSubmitted = false;

    this.groupService.fetchGroups().subscribe(groupData => {
      this.groups = groupData;
    })

    this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.isEdited = params['id'] != null;
      this.initForm();
    })
  }

  private

  initForm() {
    this.projectForm = new FormGroup({
      'projectNumber': new FormControl('', [Validators.required, Validators.maxLength(4), Validators.min(0), Validators.pattern('[0-9]+')],),
      'projectName': new FormControl('', [Validators.required, Validators.maxLength(50)]),
      'customer': new FormControl('', [Validators.required, Validators.maxLength(50)]),
      'group': new FormControl('', [Validators.required]),
      'member': new FormControl('', [Validators.pattern('[a-zA-Z,]+[a-zA-Z]'),]),
      'status': new FormControl(this.defaultStatus,),
      'startDate': new FormControl('', [Validators.required]),
      'endDate': new FormControl('',),
      'version': new FormControl(0,),
    })
    if (this.isEdited) {
      this.projectService.fetchProjectById(this.id).subscribe(data => {
        this.project = data;
        this.projectForm.setValue({
          projectNumber: this.project.projectNumber,
          projectName: this.project.projectName,
          customer: this.project.customer,
          group: this.project.group,
          member: this.project.member,
          status: this.project.status,
          startDate: moment(this.project.startDate, "DD/MM/YYYY").format('YYYY-MM-DD'),
          endDate: moment(this.project.endDate, "DD/MM/YYYY").format('YYYY-MM-DD'),
          version: this.project.version,
        })
      })
    }


  }

  onSubmit() {
    const data = this.projectForm.value;
    data.member = this.projectForm.value.member.trim().split(',');
    this.projectService.save(data).subscribe( res => {
      console.log(res);
    })
  }

  onCancel() {
      this.projectForm.reset();
  }
}
