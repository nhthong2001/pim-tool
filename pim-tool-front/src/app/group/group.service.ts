import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Group} from "./group.model";
import {map} from "rxjs";

@Injectable({
    providedIn: "root"
  }
)
export class GroupService {
  constructor(private http: HttpClient) {

  }

  fetchGroups() {
    return this.http
      .get<Group[]>("http://localhost:8080/groups")
      .pipe(
        map(data => {
          const listGroup: Group[] = [];
          for (let i in data) {
            listGroup.push({...data[i]});
          }
          return listGroup;
        })
      )
  }
}
