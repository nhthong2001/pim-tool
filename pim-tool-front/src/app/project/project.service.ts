import {Injectable} from "@angular/core";
import {Project} from "./project.model";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: "root"
})
export class ProjectService {
  private  url = "http://localhost:8080/projects";
  constructor(private http: HttpClient) {
  }


  fetchProjects() {
    return this.http
      .get(this.url)
      .pipe(
        map(res => {
          const listProject: Project[] = [];
          for (let i in res) {
            listProject.push({...res[i]});
          }
          return listProject;
        })
      )
      ;
  }

  fetchProjectById(id: number) {
    return this.http
      .get<Project>(this.url + '/' + id);
  }

  update(project: Project) {
    return this.http.put(this.url + '/update', project)
  }

  save(project: Project){
    return this.http.post<string>(this.url, project);
  }

}
