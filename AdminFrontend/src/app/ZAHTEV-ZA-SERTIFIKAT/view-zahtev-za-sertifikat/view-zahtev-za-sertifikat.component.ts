import { Ekstenzije } from './../../MODEL/Ekstenzije';
import { formatDate } from '@angular/common';
import { Component, Inject, LOCALE_ID } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { ZaKorisnika } from 'src/app/MODEL/ZaKorisnika';
import { ZaMojaKucaAplikacija } from 'src/app/MODEL/ZaMojaKucaAplikacija';
import { ZaUredjaj } from 'src/app/MODEL/ZaUredjaj';
import { ZahtevZaSertifikat } from 'src/app/MODEL/ZahtevZaSertifikat';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';
import { ZahtevZaSertifikatService } from 'src/app/SERVICE/zahtevZaSertifikat.service';

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
  ekstenzije = <Ekstenzije>{};
  status: boolean = true;
  status2: boolean = true;
  closeResult = '';

  constructor(
    private sertifikatService:SertifikatService,
    private zahtevZaSertifikatService:ZahtevZaSertifikatService,
    private router:Router,
    private route:ActivatedRoute,
    private fBuilder:FormBuilder,
    @Inject(LOCALE_ID) public locale: string,
    private modalService: NgbModal
  ){
    let temp=this.route.snapshot.paramMap.get('id');
    if(temp != null)
        this.id = temp;
    else
      this.id = "nista";

    this.zahtevZaSertifikatService.dajZahtevZaSertifikat(Number.parseInt(this.id)).subscribe(
        res=>{
          this.zahtev = res;
    });
    
    this.createForm = this.fBuilder.group({
      startDate: ["",[Validators.required]],
      endDate: ["",[Validators.required]],
      namena: [""],
      emailPotvrda: ["",[Validators.required]],
      organizacionaJedinica: ["",[Validators.required]],
      nazivOrganizacije: ["",[Validators.required]],
      skraceniNazivZemlje: ["",[Validators.required]],

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
    const item = sessionStorage.getItem('user');
		const jwt: JwtHelperService = new JwtHelperService();
		const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    if (info['uloga'] === "ROLE_MUSTERIJA"){
      this.zahtevZaSertifikatService.dajZahtevZaSertifikatMusterija(Number.parseInt(this.id)).subscribe(
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
          this.createForm.controls.organizacionaJedinica.setValue(this.zahtev.organizacionaJedinica);
          this.createForm.controls.nazivOrganizacije.setValue(this.zahtev.nazivOrganizacije);
          this.createForm.controls.skraceniNazivZemlje.setValue(this.zahtev.skraceniNazivZemlje);
          this.ekstenzije = this.zahtev.ekstenzije;
        }
      )
    }else if(info['uloga'] === "ROLE_ADMIN"){
      this.zahtevZaSertifikatService.dajZahtevZaSertifikat(Number.parseInt(this.id)).subscribe(
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
          this.createForm.controls.organizacionaJedinica.setValue(this.zahtev.organizacionaJedinica);
          this.createForm.controls.nazivOrganizacije.setValue(this.zahtev.nazivOrganizacije);
          this.createForm.controls.skraceniNazivZemlje.setValue(this.zahtev.skraceniNazivZemlje);
          this.ekstenzije = this.zahtev.ekstenzije;
        }
      )
    }
  }

  pokazivanjeElemenata(broj:any){
    if(broj.target.value==="Korisnik"){
      this.pokazivanje=1;
    }else if(broj.target.value==="Moja kuca aplikacija"){
      this.pokazivanje=2;
    }else if(broj.target.value==="Uredjaj"){
      this.pokazivanje=3;
    }else if(broj.target.value==="Za mene"){
      this.pokazivanje=0;
    }
  }

  update(){
    this.status2 = !this.status2;
    this.zahtev.startDate = this.createForm.value.startDate;
      this.zahtev.endDate=this.createForm.value.endDate;
      if (this.zahtev.startDate > this.zahtev.endDate){
        alert("Datum početka je posle datuma kraja!")
        this.status = !this.status; 
        return;
      }
      
      this.zahtev.namena=this.createForm.value.namena;
      this.zahtev.emailPotvrda=this.createForm.value.emailPotvrda;
      this.zahtev.organizacionaJedinica = this.createForm.value.organizacionaJedinica;
      this.zahtev.nazivOrganizacije = this.createForm.value.nazivOrganizacije;
      this.zahtev.skraceniNazivZemlje = this.createForm.value.skraceniNazivZemlje;
      if(this.pokazivanje==1){
        if (this.createForm.value.email === "" || this.createForm.value.ime === "" || this.createForm.value.prezime === ""){
          alert("Polja za korisnika su prazna!")
          this.status = !this.status; 
          return;
        }
        this.zahtev.zaKorisnika = <ZaKorisnika>{};
        this.zahtev.zaKorisnika.email=this.createForm.value.email;
        this.zahtev.zaKorisnika.ime=this.createForm.value.ime;
        this.zahtev.zaKorisnika.prezime=this.createForm.value.prezime;
      }else if(this.pokazivanje==2){
        if(this.createForm.value.serijskiBroj === ""){
          alert("Polja za moja kuca aplikacija su prazna!")
          this.status = !this.status; 
          return;
        }
        this.zahtev.zaMojaKucaAplikacija = <ZaMojaKucaAplikacija>{};
        this.zahtev.zaMojaKucaAplikacija.serijskiBroj=this.createForm.value.serijskiBroj;
      }else if(this.pokazivanje==3){
        if (this.createForm.value.naziv === "" || this.createForm.value.svrha === "" || this.createForm.value.serijskiBroj === ""){
          alert("Polja za uredjaj su prazna!")
          this.status = !this.status; 
          return;
        }
        this.zahtev.zaUredjaj = <ZaUredjaj>{};
        this.zahtev.zaUredjaj.naziv=this.createForm.value.naziv;
        this.zahtev.zaUredjaj.svrha=this.createForm.value.svrha;
        this.zahtev.zaUredjaj.serijskiBroj=this.createForm.value.serijskiBroj;
      }
      this.zahtevZaSertifikatService.updateZahtevZaSertifikat(this.zahtev).subscribe(
        res=>{
          alert("Uspesno promenjeno!");
          this.router.navigate(['/viewZahtevZaSertifikat/'+this.id]);
          this.status2 = !this.status2;
        },error =>{
          alert("Nisu pravilno uneti podaci!")
          this.status = !this.status;
        }
      );
      
  }

  natrag(){
    this.router.navigate(['/viewAllZahtevSertifikat']);
  }

  napraviSertifikat(){
    this.status = !this.status;
    this.sertifikatService.createSertifikat(this.zahtev).subscribe(
      res=>{
        this.router.navigate(['/viewAllSertifikat']);
      },
      error=>{
        alert("Pogresno uneti podaci!")
        this.status = !this.status;
      }
    );
    
  }

  odbijZahtev(){
    this.status = !this.status;
    this.zahtevZaSertifikatService.izbrisiZahtev(Number.parseInt(this.id)).subscribe(
      res=>{
        this.router.navigate(['/viewAllZahtevSertifikat']);
      }
    )
  }

  getRole():string{
    const item = sessionStorage.getItem('user');

    if(!item){
      return "";
    }

    const jwt:JwtHelperService = new JwtHelperService();
    const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    return info['uloga'];
  }

  open(content:any) {
    this.modalService.open(content,
   {ariaLabelledBy: 'modal-basic-title'}).result.then( 
    result =>  { 
      this.closeResult = `Closed with: ${result}`;
    }, (reason) => {
      this.closeResult = 
         `Dismissed ${this.getDismissReason(reason)}`;
    });
  }
  
  private getDismissReason(reason: any): string {
    if (reason === ModalDismissReasons.ESC) {
      return 'by pressing ESC';
    } else if (reason === ModalDismissReasons.BACKDROP_CLICK) {
      return 'by clicking on a backdrop';
    } else {
      return `with: ${reason}`;
    }
  }

}
