import {Injectable} from "@angular/core";
import {Project} from "./project.model";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {map} from "rxjs";

@Injectable({
  providedIn: "root"
})
export class ProjectService {
  private url = "http://localhost:8080/projects";

  constructor(private http: HttpClient) {
  }


  fetchProjects() {
    return this.http
      .get<Project []>(this.url);
  }

  fetchProjectById(id: number) {
    return this.http
      .get<Project>(this.url + '/' + id);
  }

  update(project: Project) {
    return this.http.put(this.url, project);
  }

  save(project: Project) {
    return this.http.post(this.url, project);
  }

  isValidProjectNumber(projectNumber: number) {
    return this.http.get<boolean>(this.url + '/isValidProjectNumber/' + projectNumber);
  }

  searchProject(projectInfo: string, status: string) {
    if (status !== undefined) {
      return this.http.get<Project[]>(this.url + '/search', {params: {keyword: projectInfo, status: status}});
    } else {
      return this.http.get<Project[]>(this.url + '/searchByKeyword', {params: {keyword: projectInfo}});
    }
  }

  deleteProject(projectId: number) {
    return this.http.delete(this.url + '/' + projectId);
  }

  deleteListProject(listCheckedProjectId: number[]) {
    const options = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json',
      }),
      body: listCheckedProjectId
      ,
    };
    return this.http.delete(this.url + '/deleteListProject', options);
  }
}
