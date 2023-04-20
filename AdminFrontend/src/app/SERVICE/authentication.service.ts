import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Musterija } from '../MODEL/Musterija';

@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
	private path = "http://localhost:8080/auth";
	constructor(
		private http: HttpClient
	) { }

	login(auth: any): Observable<any> {
		return this.http.post(this.path+'/log-in', {username: auth.username, password: auth.password}, {headers: this.headers, responseType: 'json'});
	}

	logout(): Observable<any> {
		return this.http.get(this.path+'/log-out', {headers: this.headers, responseType: 'text'});
	}

	register(musterija:Musterija){
		return this.http.post(this.path+"/register",musterija);
	}

	isLoggedIn(): boolean {
		if (!localStorage.getItem('user')) {
				return false;
		}
		return true;
    }

}