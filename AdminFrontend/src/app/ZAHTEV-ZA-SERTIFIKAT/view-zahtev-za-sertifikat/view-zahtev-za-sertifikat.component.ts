import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
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

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router,
    private route:ActivatedRoute,
    private fBuilder:FormBuilder
  ){
    let temp=this.route.snapshot.paramMap.get('id');
    if(temp != null)
        this.id = temp;
    else
      this.id = "nista";
    this.createForm = this.fBuilder.group({});
  }

  ngOnInit():void{
    this.sertifikatService.dajZahtevZaSertifikat(Number.parseInt(this.id)).subscribe(
      res=>{
        if(res.namena==="Korisnik")
          this.pokazivanje=1;
        else if(res.namena==="Moja kuca aplikacija")
          this.pokazivanje=2;
        else if(res.namena==="Uredjaj")
          this.pokazivanje=3;
          
        this.createForm = this.fBuilder.group({
          startDate: [res.startDate,[Validators.required]],
          endDate: [res.endDate,[Validators.required]],
          namena: [res.namena],
          emailPotvrda: [res.emailPotvrda,[Validators.required]],
    
          email: [res.zaKorisnika.email,[Validators.required]],
          ime: [res.zaKorisnika.ime,[Validators.required]],
          prezime: [res.zaKorisnika.prezime,[Validators.required]],
    
          serijskiBroj: [res.zaMojaKucaAplikacija.serijskiBroj,[Validators.required]],
    
          naziv: [res.zaUredjaj.naziv,[Validators.required]],
          svrha: [res.zaUredjaj.svrha,[Validators.required]],
          //serijskiBroj: ["",[Validators.required]]
        });
      }
    )
  }

  pokazivanjeElemenata(broj:any){
    if(broj.target.value==1){
      this.pokazivanje=1;
    }else if(broj.target.value==2){
      this.pokazivanje=2;
    }else if(broj.target.value==3){
      this.pokazivanje=3;
    }else if(broj.target.value==0){
      this.pokazivanje=0;
    }
  }

  update(){
    this.zahtev.startDate = this.createForm.value.startDate;
      this.zahtev.endDate=this.createForm.value.endDate;
      this.zahtev.namena=this.createForm.value.namena;
      this.zahtev.emailPotvrda=this.createForm.value.emailPotvrda;
      this.zahtev.potvrdjenZahtev = false;
      this.zahtev.prihvacen=false;
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
      this.sertifikatService.updateZahtevZaSertifikat(this.zahtev);
      this.router.navigate(['/viewAllZahtevSertifikat']);
  }

  delete2(){
    this.sertifikatService.izbrisiZahtevZaSertifikat(Number.parseInt(this.id));
    this.router.navigate(['/viewAllZahtevSertifikat']);
  }

  natrag(){
    this.router.navigate(['/viewAllZahtevSertifikat']);
  }

}
