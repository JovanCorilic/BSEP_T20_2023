import { Musterija } from './../../MODEL/Musterija';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from './../../SERVICE/authentication.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerForm: FormGroup;
  status: boolean = true;
  musterija = <Musterija>{};

  constructor(
    private authenticationService:AuthenticationService,
    private router:Router,
    private fBuilder:FormBuilder
  ){
    this.registerForm = this.fBuilder.group({
      ime: ["",[Validators.required]],
      prezime: ["",[Validators.required]],
      email: ["",[Validators.required,Validators.email]],
      lozinka: ["",[Validators.required, Validators.minLength(12)]]
    })
  }

  register(){
    this.status = !this.status;
    this.musterija.email = this.registerForm.value.email;
    this.musterija.ime = this.registerForm.value.ime;
    this.musterija.prezime = this.registerForm.value.prezime;
    this.musterija.lozinka = this.registerForm.value.lozinka;
    this.authenticationService.register(this.musterija).subscribe(
      res=>{
        this.router.navigate(['']);
      }
    )
  }

}
