import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from "@angular/common/http";
import { catchError } from 'rxjs/operators'; // RESEARCH THIS
import { Observable, throwError } from 'rxjs'; // RESEARCH THIS

// models
import { Commander } from '../../../shared/models/commander';

@Injectable({
  providedIn: 'root'
})
export class CommanderLoginService {

  private headers = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  login(commander: Commander): Observable<Commander> {
    const url = 'http://localhost:8765/player/login' //environment.customerAPIUrl + '/login'; pull from environment later

    return this.http.post<Commander>(url,commander,{headers:this.headers})
    .pipe(catchError(this.handleError));

  }

  updatePassword(commander: Commander): Observable<Commander> {
    const url = "http://localhost:8765/player/alter";
    
    return this.http.put<Commander>(url, commander, {headers:this.headers})
    .pipe(catchError(this.handleError));
  }

  registerCommander(commander: Commander): Observable<Commander> {
    const url = "http://localhost:8765/player/alter";

    return this.http.post<Commander>(url, commander, {headers:this.headers})
    .pipe(catchError(this.handleError));    
  }

private handleError(err: HttpErrorResponse) {
    console.log(err)
    let errMsg:string='';
    if (err.error instanceof Error) {   
        errMsg=err.error.message;
        console.log(errMsg)
    }
     else if(typeof err.error === 'string'){
        errMsg = JSON.parse(err.error).errorMessage
    }
    else {
       if(err.status==0){ 
           errMsg="A connection to back end can not be established.";
       }else{
        errMsg = err.error.errorMessage;
       }
     }
        return throwError(errMsg);
}
}
