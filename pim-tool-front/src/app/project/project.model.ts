export class Project {
  public id: number;
  public projectName: string;
  public projectNumber: number;
  public customer: string;
  public group: string;
  public member: string[];
  public status: string;
  public startDate: Date;
  public endDate: Date;

  constructor(
    id: number,
    projectName: string,
    projectNumber: number,
    customer: string,
    group: string,
    member: string[],
    status: string,
    startDate: Date,
    endDate: Date
  ) {
  }
}
