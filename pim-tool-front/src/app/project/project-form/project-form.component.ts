import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import * as moment from 'moment';

import {GroupService} from "../../group/group.service";
import {ProjectService} from "../project.service";
import {Project} from "../project.model";
import {Group} from "../../group/group.model";
import {EmployeeService} from "../../employee/employee.service";
import {ProjectStatus} from "../project-status.enum";

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.css']
})
export class ProjectFormComponent implements OnInit {
  project: Project;
  isEdited = false;
  isValidProjectNumber = true;
  isAlertMandatoryError = false;
  isInValidDateEnd = false;
  isAlertProjectNumberError = false;
  inValidVisa: string[] = [];
  validVisa: string[] = [];
  listInvalidVisa: string;
  id: number;
  ProjectStatus: ProjectStatus;
  status: ProjectStatus[] = [ProjectStatus.NEW, ProjectStatus.PLA, ProjectStatus.INP, ProjectStatus.FIN];
  defaultStatus: ProjectStatus = ProjectStatus.NEW;
  groups: Group[] = [];
  projectForm: FormGroup;
  projectControls = {
    'id': new FormControl(''),
    'projectNumber': new FormControl('', [Validators.required, this.forbiddenProjectNumber]),
    'projectName': new FormControl('', [Validators.required, Validators.maxLength(50)]),
    'customer': new FormControl('', [Validators.required, Validators.maxLength(50)]),
    'group': new FormControl('', [Validators.required]),
    'member': new FormControl(''),
    'status': new FormControl(this.defaultStatus, Validators.required),
    'startDate': new FormControl('', [Validators.required]),
    'endDate': new FormControl('',),
    'version': new FormControl(0,),
  }

  constructor(private projectService: ProjectService,
              private groupService: GroupService,
              private employeeService: EmployeeService,
              private route: ActivatedRoute,
              private router: Router) {
    this.project = new Project();
  }

  ngOnInit(): void {
    this.inValidVisa = [];

    this.groupService.fetchGroups().subscribe(groupData => {
      this.groups = groupData;
    });

    this.employeeService.fetchEmployees().subscribe(employees => {
      employees.forEach(employee => {
        this.validVisa.push(employee.visa.toUpperCase());
      });
    });

    this.route.params.subscribe(params => {
      this.id = +params['id'];
      this.isEdited = params['id'] != null;
      this.initForm();

    })
  }


  initForm() {
    this.projectForm = new FormGroup(this.projectControls);
    if (this.isEdited) {
      this.projectService.fetchProjectById(this.id).subscribe(data => {
        this.project = data;
        const sd = moment(this.project.startDate, "DD/MM/YYYY").format('YYYY-MM-DD');
        const ed = moment(this.project.endDate, "DD/MM/YYYY").format('YYYY-MM-DD');
        const members = this.project.member.join();
        this.projectForm.setValue({
          id: this.project.id,
          projectNumber: this.project.projectNumber,
          projectName: this.project.projectName,
          customer: this.project.customer,
          group: this.project.group,
          member: members,
          status: this.project.status,
          startDate: sd === 'Invalid date' ? '' : sd,
          endDate: ed === 'Invalid date' ? '' : ed,
          version: this.project.version
        })

        this.projectControls.projectNumber.disable();
      })
    }
  }


  forbiddenProjectNumber(control: FormControl) {
    if (control.value <= 0) {
      return {forbidenNumber: true};
    }
    return null;
  }

  onSubmit() {
    if (!this.isValidData(this.projectForm)) {
      return;
    }
    let data = this.projectForm.getRawValue();

    data.member = this.projectForm.getRawValue().member.split(',')
      .filter(mem => mem.trim() !== '')
      .map(mem => mem.toUpperCase().trim());


    if (this.isEdited) {
      this.projectService.update(data).subscribe(res => {
        this.router.navigate(['/project/project-list'], {relativeTo: this.route});
      }, error => {
        alert(error.error.message);
      })
    } else {
      this.projectService.save(data).subscribe(res => {
        this.router.navigate(['/project/project-list'], {relativeTo: this.route});
      }, error => {
        // Display error on interface
      })
    }
  }

  private isValidData(formData) {
    this.inValidVisa = [];
    let formValue = formData.value;
    formData.markAllAsTouched();

    if (formValue.projectNumber === '') {
      this.isAlertMandatoryError = true;
    } else {
      if (!this.isEdited) {
        this.projectService.isValidProjectNumber(formValue.projectNumber).subscribe(check => {
          this.isValidProjectNumber = check;
          if (!check) {
            formData.controls.projectNumber.setErrors({isNotValidNumber: true});
            this.isAlertMandatoryError = true;
            this.isAlertProjectNumberError = true;
          }
        });
      }
    }
    if (formValue.customer === '') {
      this.isAlertMandatoryError = true;
    }
    if (formValue.group === '') {
      this.isAlertMandatoryError = true;
    }

    if (formValue.startDate === '') {
      this.isAlertMandatoryError = true;
    } else {
      if (formValue.endDate.length !== '') {
        if (new Date(formValue.startDate) > new Date(formValue.endDate)) {
          this.isInValidDateEnd = true;
          formData.controls.endDate.setErrors({'invalidEndDate': true})
        }
      }
    }

    if (formValue.member.trim() !== '') {
      formValue.member.split(',').map(member => member.trim()).forEach(member => {
        if (!this.validVisa.includes(member.toUpperCase()) && member != '') {
          if (!this.inValidVisa.includes(member)) {
            this.inValidVisa.push(member);
          }
        }
      });

      if (this.inValidVisa.length !== 0) {
        this.listInvalidVisa = this.inValidVisa.join(', ');
        formData.controls.member.setErrors({invalidVisa: true});
      }
    }
    if (formValue.projectNumber !== ''
      && formValue.projectName !== ''
      && formValue.customer !== ''
      && formValue.group !== ''
      && formValue.status !== ''
      && formValue.startDate !== '') {
      this.isAlertMandatoryError = false;
    }
    return formData.valid;
  }

  onCancel() {
    if (confirm("Are you sure cancel?")) {
      this.router.navigate(['/project/project-list'], {relativeTo: this.route})
    }
  }

  closeAlertError() {
    this.isAlertMandatoryError = false;
  }

  onInputProjectNumber() {
    this.isAlertProjectNumberError = false;
  }
  projectStatusEnumToKey(status: ProjectStatus){
    return ProjectStatus[status];
  }
}
