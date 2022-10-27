import {ProjectStatus} from "./project-status.enum";

export class Project {
  public id: number;
  public projectName: string;
  public projectNumber: number;
  public customer: string;
  public group: string;
  public member: string[];
  public status: ProjectStatus;
  public startDate: Date;
  public endDate: Date;
  public version?: number;

  constructor() {
  }
}
