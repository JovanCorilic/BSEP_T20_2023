import { Musterija } from './../../MODEL/Musterija';
import { AbstractControl, FormBuilder, FormGroup, ValidatorFn, Validators } from '@angular/forms';
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
      lozinka: ["",[Validators.required, Validators.minLength(12),this.daLiImaBroj(),this.daLiImaSpecijalanKarakter()]]
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

  notANumber(): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      const value = control.value
      let nV = value
      if (typeof value == 'string') {
        nV = value.replace(',', '.')
      }
      return (Number.isNaN(Number(nV)) && !control.pristine) ? {notANumber: true} : null;
    };
  }

  daLiImaBroj(): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      const value = control.value
      let nV = value
      if (typeof value == 'string') {
        nV = value.replace(',', '.')
      }
      const hasNumbers = (str: string): boolean => {
        const regex = /\d/;
        return regex.test(str);
      }

      return (!hasNumbers(nV) && !control.pristine) ? {daLiImaBroj: true} : null;
    };
  }

  daLiImaSpecijalanKarakter(): ValidatorFn {
    return (control: AbstractControl): {[key: string]: any} | null => {
      const value = control.value
      let nV = value
      if (typeof value == 'string') {
        nV = value.replace(',', '.')
      }
      const hasSpecialChars = (str: string): boolean => {
        const regex = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]/;
        return regex.test(str);
      }

      return (!hasSpecialChars(nV) && !control.pristine) ? {daLiImaSpecijalanKarakter: true} : null;
    };
  }

  

}
