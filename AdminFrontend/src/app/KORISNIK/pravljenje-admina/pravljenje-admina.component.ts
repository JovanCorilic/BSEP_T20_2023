import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Musterija } from 'src/app/MODEL/Musterija';
import { KorisnikService } from 'src/app/SERVICE/korisnik.service';

@Component({
  selector: 'app-pravljenje-admina',
  templateUrl: './pravljenje-admina.component.html',
  styleUrls: ['./pravljenje-admina.component.css']
})
export class PravljenjeAdminaComponent {
  adminRegisterForm: FormGroup;
  status: boolean = true;
  musterija = <Musterija>{};

  constructor(
    private korisnikService:KorisnikService,
    private router:Router,
    private fBuilder:FormBuilder
  ){
    this.adminRegisterForm = this.fBuilder.group({
      ime: ["",[Validators.required]],
      prezime: ["",[Validators.required]],
      email: ["",[Validators.required]],
      lozinka: ["",[Validators.required]]
    })
  }

  napraviAdmina(){
    this.status = !this.status;
    this.musterija.email = this.adminRegisterForm.value.email;
    this.musterija.ime = this.adminRegisterForm.value.ime;
    this.musterija.prezime = this.adminRegisterForm.value.prezime;
    this.musterija.lozinka = this.adminRegisterForm.value.lozinka;
    this.korisnikService.pravljenjeAdmina(this.musterija).subscribe(
      res=>{
        this.router.navigate(['']);
      }
    )
  }

}
