import { ZahtevZaSertifikat } from '../../MODEL/ZahtevZaSertifikat';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ZaKorisnika } from 'src/app/MODEL/ZaKorisnika';
import { ZaMojaKucaAplikacija } from 'src/app/MODEL/ZaMojaKucaAplikacija';
import { ZaUredjaj } from 'src/app/MODEL/ZaUredjaj';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';
import { ZahtevZaSertifikatService } from 'src/app/SERVICE/zahtevZaSertifikat.service';
import {NgbModal, ModalDismissReasons} from '@ng-bootstrap/ng-bootstrap';
import { Templejt } from 'src/app/MODEL/Templejt';
import { Ekstenzije } from 'src/app/MODEL/Ekstenzije';
import { AuthorityKeyIdentifierEkstenzije } from 'src/app/MODEL/EKSTENZIJE/AuthorityKeyIdentifierEkstenzije';
import { BasicConstraintsEkstenzije } from 'src/app/MODEL/EKSTENZIJE/BasicConstraintsEkstenzije';
import { ExtendedKeyUsageEkstenzije } from 'src/app/MODEL/EKSTENZIJE/ExtendedKeyUsageEkstenzije';
import { KeyUsageEkstenzije } from 'src/app/MODEL/EKSTENZIJE/KeyUsageEkstenzije';
import { SubjectAlternativeNameEkstenzije } from 'src/app/MODEL/EKSTENZIJE/SubjectAlternativeNameEkstenzije';
import { SubjectKeyIdentifierEkstenzije } from 'src/app/MODEL/EKSTENZIJE/SubjectKeyIdentifierEkstenzije';

@Component({
  selector: 'app-create-zahtev-za-sertifikat',
  templateUrl: './create-zahtev-za-sertifikat.component.html',
  styleUrls: ['./create-zahtev-za-sertifikat.component.css']
})
export class CreateZahtevZaSertifikatComponent {
  createForm: FormGroup;
  pokazivanje:number = 0;
  zahtev = <ZahtevZaSertifikat>{};
  ekstenzije:Ekstenzije;
  status: boolean = true;
  closeResult = '';
  templejt = new Templejt();
  izabranTemplejt = 0;
  basicConstraintsForm : FormGroup;
  extendedKeyUsageForm : FormGroup;
  keyUsageForm : FormGroup;
  subjectAlternativeNameForm : FormGroup;

  constructor(
    private sertifikatService:SertifikatService,
    private zahtevZaSertifikatService:ZahtevZaSertifikatService,
    private router:Router,
    private fBuilder:FormBuilder,
    private modalService: NgbModal
  ){
    this.zahtev.ekstenzije = <Ekstenzije>{};
    this.ekstenzije = this.zahtev.ekstenzije;
    this.ekstenzije.authorityKeyIdentifierEkstenzije = <AuthorityKeyIdentifierEkstenzije>{};
    this.ekstenzije.basicConstraintsEkstenzije = <BasicConstraintsEkstenzije>{};
    this.ekstenzije.extendedKeyUsageEkstenzije = <ExtendedKeyUsageEkstenzije>{};
    this.ekstenzije.keyUsageEkstenzije = <KeyUsageEkstenzije>{};
    this.ekstenzije.subjectAlternativeNameEkstenzije = <SubjectAlternativeNameEkstenzije>{};
    this.ekstenzije.subjectKeyIdentifierEkstenzije = <SubjectKeyIdentifierEkstenzije>{};

    this.createForm = this.fBuilder.group({
      startDate: ["",[Validators.required]],
      endDate: ["",[Validators.required]],
      namena: ["Admin aplikacija"],
      emailPotvrda: ["",[Validators.required]],

      email: [""],
      ime: [""],
      prezime: [""],

      serijskiBroj: [""],

      naziv: [""],
      svrha: [""],
      //serijskiBroj: ["",[Validators.required]]
    })

    this.basicConstraintsForm = this.fBuilder.group({
      isCA : true,
      maxPathLen : ["",[Validators.required]]
    })

    this.extendedKeyUsageForm = this.fBuilder.group({
      anyExtendedKeyUsage : false,
      id_kp_codeSigning : false,
      id_kp_emailProtection: false,
      id_kp_ipsecEndSystem: false,
      id_kp_ipsecUser: false,
      id_kp_timeStamping: false,
      id_kp_OCSPSigning: false,
      id_kp_smartcardlogon: false
    })

    this.keyUsageForm = this.fBuilder.group({
      digitalSignature: false,
      nonRepudiation: false,
      keyEncipherment: false,
      dataEncipherment: false,
      keyAgreement: false,
      keyCertSign: false,
      cRLSign: false,
      encipherOnly: false,
      decipherOnly: false
    })

    this.subjectAlternativeNameForm = this.fBuilder.group({
      
    })

  }

  ngOnInit():void{
    
    
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

  pokazivanjeTemplejta(templejtNaziv:any){
    this.templejt = new Templejt();
    if(templejtNaziv.target.value==="CA"){
      this.templejt.authorityKeyIdentifierEkstenzije = true;
      this.templejt.basicConstraintsEkstenzije = true;
      this.templejt.keyUsageEkstenzije = true;
      this.templejt.subjectKeyIdentifierEkstenzije = true;
      this.izabranTemplejt = 1;
    }else if(templejtNaziv.target.value==="SSL Server"){
      this.templejt.authorityKeyIdentifierEkstenzije = true;
      this.templejt.extendedKeyUsageEkstenzije = true;
      this.templejt.keyUsageEkstenzije = true;
      this.templejt.subjectAlternativeNameEkstenzije = true;
      this.templejt.subjectKeyIdentifierEkstenzije = true;
      this.izabranTemplejt = 2;
    }else if(templejtNaziv.target.value==="SSL Client"){
      this.templejt.authorityKeyIdentifierEkstenzije = true;
      this.templejt.extendedKeyUsageEkstenzije = true;
      this.templejt.keyUsageEkstenzije = true;
      this.templejt.subjectKeyIdentifierEkstenzije = true;
      this.izabranTemplejt = 3;
    }else if(templejtNaziv.target.value==="Code Signing"){
      this.templejt.authorityKeyIdentifierEkstenzije = true;
      this.templejt.extendedKeyUsageEkstenzije = true;
      this.templejt.keyUsageEkstenzije = true;
      this.templejt.subjectKeyIdentifierEkstenzije = true;
      this.izabranTemplejt = 4;
    }
  }

  napravi(){
      this.status = !this.status; 
      this.zahtev.startDate = this.createForm.value.startDate;
      this.zahtev.endDate=this.createForm.value.endDate;
      this.zahtev.namena=this.createForm.value.namena;
      this.zahtev.emailPotvrda=this.createForm.value.emailPotvrda;
      this.zahtev.potvrdjenZahtev = false;
      this.zahtev.prihvacen=false;
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
      this.zahtevZaSertifikatService.createZahtevZaSertifikat(this.zahtev).subscribe(
        res=>{
          this.router.navigate(['/viewAllZahtevSertifikat']);
        }
      );
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
