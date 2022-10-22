import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {GroupService} from "../../group/group.service";

@Component({
  selector: 'app-project-form',
  templateUrl: './project-form.component.html',
  styleUrls: ['./project-form.component.css']
})
export class ProjectFormComponent implements OnInit {
  status: string[] = ['New', 'Planned', 'In progress', 'Finished'];
  defaultStatus: string = 'New';
  groups: string[] = [];
  projectForm: FormGroup;

  constructor(private groupService: GroupService) {
  }

  ngOnInit(): void {
    this.projectForm = new FormGroup({
      'projectNumber': new FormControl(null),
      'projectName': new FormControl(null),
      'customer': new FormControl(null),
      'group': new FormControl(null),
      'member': new FormControl(null),
      'status': new FormControl(null),
      'startDate': new FormControl(null),
      'endDate': new FormControl(null)
    })
    this.projectForm.controls['status'].setValue(this.defaultStatus, {onlySelf: true});
    let listGroups = this.groupService.getGroups();
    for (let i in listGroups) {
      this.groups.push(listGroups[i].leader_visa);
    }
  }

  onSubmit() {

  }

  onCancel() {

  }
}
