import { ZahtevZaSertifikat } from '../../MODEL/ZahtevZaSertifikat';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ZaKorisnika } from 'src/app/MODEL/ZaKorisnika';
import { ZaMojaKucaAplikacija } from 'src/app/MODEL/ZaMojaKucaAplikacija';
import { ZaUredjaj } from 'src/app/MODEL/ZaUredjaj';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';

@Component({
  selector: 'app-create-zahtev-za-sertifikat',
  templateUrl: './create-zahtev-za-sertifikat.component.html',
  styleUrls: ['./create-zahtev-za-sertifikat.component.css']
})
export class CreateZahtevZaSertifikatComponent {
  createForm: FormGroup;
  pokazivanje:number = 0;
  zahtev = <ZahtevZaSertifikat>{};

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router,
    private fBuilder:FormBuilder
  ){
    this.createForm = this.fBuilder.group({
      startDate: ["",[Validators.required]],
      endDate: ["",[Validators.required]],
      namena: "Admin aplikacija",
      emailPotvrda: ["",[Validators.required]],

      email: ["",[Validators.required]],
      ime: ["",[Validators.required]],
      prezime: ["",[Validators.required]],

      serijskiBroj: ["",[Validators.required]],

      naziv: ["",[Validators.required]],
      svrha: ["",[Validators.required]],
      //serijskiBroj: ["",[Validators.required]]
    })
  }

  pokazivanjeElemenata(broj:any){
    if(broj.target.value==="Korisnik"){
      this.pokazivanje=1;
    }else if(broj.target.value==="Moja kuca aplikacija"){
      this.pokazivanje=2;
    }else if(broj.target.value==="Uredjaj"){
      this.pokazivanje=3;
    }else if(broj.target.value==="Admin aplikacija"){
      this.pokazivanje=0;
    }
  }

  napravi(){
      this.zahtev.startDate = this.createForm.value.startDate;
      this.zahtev.endDate=this.createForm.value.endDate;
      this.zahtev.namena=this.createForm.value.namena;
      this.zahtev.emailPotvrda=this.createForm.value.emailPotvrda;
      this.zahtev.potvrdjenZahtev = false;
      this.zahtev.prihvacen=false;
      this.zahtev.zaKorisnika=<ZaKorisnika>{};
      this.zahtev.zaMojaKucaAplikacija = <ZaMojaKucaAplikacija>{};
      this.zahtev.zaUredjaj = <ZaUredjaj>{};
      if(this.pokazivanje==1){
        this.zahtev.zaKorisnika.email=this.createForm.value.email;
        this.zahtev.zaKorisnika.ime=this.createForm.value.ime;
        this.zahtev.zaKorisnika.prezime=this.createForm.value.prezime;
      }else if(this.pokazivanje==2){
        this.zahtev.zaMojaKucaAplikacija.serijskiBroj=this.createForm.value.serijskiBroj;
      }else if(this.pokazivanje==3){
        this.zahtev.zaUredjaj.naziv=this.createForm.value.naziv;
        this.zahtev.zaUredjaj.svrha=this.createForm.value.svrha;
        this.zahtev.zaUredjaj.serijskiBroj=this.createForm.value.serijskiBroj;
      }
   
      //let temp:ZahtevZaSertifikat = new ZahtevZaSertifikat(0,new Date(),new Date(),"","",false,false,new ZaKorisnika(0,"","",""),new ZaUredjaj(0,"","",""),new ZaMojaKucaAplikacija(0,""));
      this.sertifikatService.createZahtevZaSertifikat(this.zahtev).subscribe(
        res=>{
          this.router.navigate(['/viewAllZahtevSertifikat']);
        }
      );
      
  }
}
