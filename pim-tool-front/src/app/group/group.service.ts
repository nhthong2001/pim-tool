import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Group} from "./group.model";

@Injectable({
  providedIn: "root"
  }
)
export class GroupService {
  listGroup: Group[] = [];

  constructor(private http: HttpClient) {
    this.http.get("http://localhost:8080/groups").subscribe(res => {
      for (let i in res) {
        this.listGroup.push({...res[i]});
      }
    })
  }

  getGroups() {
    return this.listGroup;
  }
}
