import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Employee} from "./employee.model";
import {map} from "rxjs";

@Injectable({
    providedIn: "root"
  }
)
export class EmployeeService {
  constructor(private http: HttpClient) {

  }

  fetchEmployees() {
    return this.http
      .get<Employee[]>("http://localhost:8080/employees")
      .pipe(
        map(data => {
          const listEmployees: Employee[] = [];
          for (let i in data) {
            listEmployees.push({...data[i]});
          }
          return listEmployees;
        })
      )
  }
}
