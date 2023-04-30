import { Injectable, resolveForwardRef } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from '../SERVICE/authentication.service';

@Injectable()
export class Interceptor implements HttpInterceptor {

	intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

		const item = sessionStorage.getItem('accessToken');
		//const decodedItem = JSON.parse(item);

		if (item) {
			req = req.clone({
				setHeaders: {
				  Authorization: `Bearer ${item}`
				},
				withCredentials: true
			  });
			}
		
		return next.handle(req);
	}
}