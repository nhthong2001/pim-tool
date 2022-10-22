import {Injectable} from "@angular/core";
import {Project} from "./project.model";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: "root"
})
export class ProjectService {
  private listProject: Project[] = [];

  constructor(private http: HttpClient) {
    this.http.get("http://localhost:8080/projects").subscribe(res => {
      for (let i in res) {
        this.listProject.push({...res[i]});
      }
    })
  }


  getProjects() {
    return this.listProject;
  }


}
