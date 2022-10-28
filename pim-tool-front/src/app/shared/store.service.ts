import {Injectable} from '@angular/core';
import {Project} from "../project/project.model";
import {ProjectStatus} from "../project/project-status.enum";

@Injectable({
  providedIn: 'root'
})
export class StoreService {

  searchInfo: string = '';
  searchStatus: ProjectStatus = null;

  constructor() {
  }

  public saveData(searchInfo: string, searchStatus: ProjectStatus) {
    this.searchInfo = searchInfo;
    this.searchStatus = searchStatus;
  }

  public resetData() {
    this.searchInfo = '';
    this.searchStatus = null;
  }

}
