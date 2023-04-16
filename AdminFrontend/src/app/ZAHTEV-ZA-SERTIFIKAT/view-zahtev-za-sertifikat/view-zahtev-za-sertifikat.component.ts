import { formatDate } from '@angular/common';
import { Component, Inject, LOCALE_ID } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ZaKorisnika } from 'src/app/MODEL/ZaKorisnika';
import { ZaMojaKucaAplikacija } from 'src/app/MODEL/ZaMojaKucaAplikacija';
import { ZaUredjaj } from 'src/app/MODEL/ZaUredjaj';
import { ZahtevZaSertifikat } from 'src/app/MODEL/ZahtevZaSertifikat';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';

@Component({
  selector: 'app-view-zahtev-za-sertifikat',
  templateUrl: './view-zahtev-za-sertifikat.component.html',
  styleUrls: ['./view-zahtev-za-sertifikat.component.css']
})
export class ViewZahtevZaSertifikatComponent {
  id=<string>{};
  createForm: FormGroup;
  pokazivanje:number = 0;
  zahtev = <ZahtevZaSertifikat>{};
  status: boolean = true;
  status2: boolean = true;

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router,
    private route:ActivatedRoute,
    private fBuilder:FormBuilder,
    @Inject(LOCALE_ID) public locale: string
  ){
    let temp=this.route.snapshot.paramMap.get('id');
    if(temp != null)
        this.id = temp;
    else
      this.id = "nista";

    this.sertifikatService.dajZahtevZaSertifikat(Number.parseInt(this.id)).subscribe(
        res=>{
          this.zahtev = res;
    });
    
    this.createForm = this.fBuilder.group({
      startDate: ["",[Validators.required]],
      endDate: ["",[Validators.required]],
      namena: [""],
      emailPotvrda: ["",[Validators.required]],

      email: [""],
      ime: [""],
      prezime: [""],

      serijskiBroj: [""],

      naziv: [""],
      svrha: [""],
      //serijskiBroj: ["",[Validators.required]]
    });
  }
//this.createForm.controls.emailPotvrda.setValue(this.zahtev.emailPotvrda);
  ngOnInit():void{
    this.sertifikatService.dajZahtevZaSertifikat(Number.parseInt(this.id)).subscribe(
      res=>{
        this.zahtev=res;
        if(this.zahtev.namena==="Korisnik"){
          this.pokazivanje=1;
          this.createForm.controls.email.setValue(this.zahtev.zaKorisnika.email);
          this.createForm.controls.ime.setValue(this.zahtev.zaKorisnika.ime);
          this.createForm.controls.prezime.setValue(this.zahtev.zaKorisnika.prezime);
        }
        else if(this.zahtev.namena==="Moja kuca aplikacija"){
          this.pokazivanje=2;
          this.createForm.controls.serijskiBroj.setValue(this.zahtev.zaMojaKucaAplikacija.serijskiBroj);
        }
        else if(this.zahtev.namena==="Uredjaj"){
          this.pokazivanje=3;
          this.createForm.controls.naziv.setValue(this.zahtev.zaUredjaj.naziv);
          this.createForm.controls.svrha.setValue(this.zahtev.zaUredjaj.svrha);
          this.createForm.controls.serijskiBroj.setValue(this.zahtev.zaUredjaj.serijskiBroj);
        }
        this.createForm.controls.startDate.setValue(formatDate(this.zahtev.startDate,'yyyy-MM-ddThh:mm',this.locale));
        this.createForm.controls.endDate.setValue(formatDate(this.zahtev.endDate,'yyyy-MM-ddThh:mm',this.locale));
        this.createForm.controls.namena.setValue(this.zahtev.namena);
        this.createForm.controls.emailPotvrda.setValue(this.zahtev.emailPotvrda);
      
      }
    )
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

  update(){
    this.status2 = !this.status2;
    this.zahtev.startDate = this.createForm.value.startDate;
      this.zahtev.endDate=this.createForm.value.endDate;
      this.zahtev.namena=this.createForm.value.namena;
      this.zahtev.emailPotvrda=this.createForm.value.emailPotvrda;

      if(this.pokazivanje==1){
        this.zahtev.zaKorisnika = <ZaKorisnika>{};
        this.zahtev.zaKorisnika.email=this.createForm.value.email;
        this.zahtev.zaKorisnika.ime=this.createForm.value.ime;
        this.zahtev.zaKorisnika.prezime=this.createForm.value.prezime;
      }else if(this.pokazivanje==2){
        this.zahtev.zaMojaKucaAplikacija = <ZaMojaKucaAplikacija>{};
        this.zahtev.zaMojaKucaAplikacija.serijskiBroj=this.createForm.value.serijskiBroj;
      }else if(this.pokazivanje==3){
        this.zahtev.zaUredjaj = <ZaUredjaj>{};
        this.zahtev.zaUredjaj.naziv=this.createForm.value.naziv;
        this.zahtev.zaUredjaj.svrha=this.createForm.value.svrha;
        this.zahtev.zaUredjaj.serijskiBroj=this.createForm.value.serijskiBroj;
      }
      this.sertifikatService.updateZahtevZaSertifikat(this.zahtev).subscribe(
        res=>{
          alert("Uspesno promenjeno!");
          this.router.navigate(['/viewZahtevZaSertifikat/'+this.id]);
          this.status2 = !this.status2;
        }
      );
      
  }

  delete2(){
    this.sertifikatService.izbrisiZahtevZaSertifikat(Number.parseInt(this.id)).subscribe();
    this.router.navigate(['/viewAllZahtevSertifikat']);
  }

  natrag(){
    this.router.navigate(['/viewAllZahtevSertifikat']);
  }

  napraviSertifikat(){
    this.status = !this.status;
    this.sertifikatService.createSertifikat(this.zahtev).subscribe(
      res=>{
        this.router.navigate(['/viewAllSertifikat']);
      }
    );
    
  }

}
