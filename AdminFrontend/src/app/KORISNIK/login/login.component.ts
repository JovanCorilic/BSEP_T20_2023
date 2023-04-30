import { Component } from '@angular/core';
import { FormBuilder, FormGroup,Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../SERVICE/authentication.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  logForm: FormGroup;
  pokazivanje:boolean=false;
  status: boolean = true;


  constructor(
    private fBuilder:FormBuilder,
    private router:Router,
    private authenticationService: AuthenticationService
  ) {
    this.logForm = this.fBuilder.group({
      email: ["",[Validators.required]],
      password: ["",[Validators.required]],
      test: ""
    });
   }

   

  ngOnInit():void {
  }

  logIn(){
    this.status= !this.status;
    console.log(this.logForm.value.test);
		const auth: any = {};
		auth.username = this.logForm.value.email;
		auth.password = this.logForm.value.password;
		this.authenticationService.login(auth).subscribe(
			result => {
        //OVAKO DOBIJEM TOKEN
        //console.log(result.headers)
        sessionStorage.setItem('email',auth.username);
        sessionStorage.setItem('user', JSON.stringify(result));
        const item = sessionStorage.getItem('user');
		    const decodedItem = JSON.parse(item!);
        sessionStorage.setItem('accessToken', decodedItem.accessToken);
        const jwt: JwtHelperService = new JwtHelperService();
        const info = jwt.decodeToken(decodedItem.accessToken);
        //console.log(info);
        sessionStorage.setItem('uloga', info['uloga']);
        //console.log(info['uloga']);
				this.router.navigate(['']);
        
			},
			error => {
	
			}
		);
	}
}
