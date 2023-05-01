import { Ekstenzije } from './../../MODEL/Ekstenzije';
import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { NgbModal, ModalDismissReasons } from '@ng-bootstrap/ng-bootstrap';
import { Sertifikat } from 'src/app/MODEL/Sertifikat';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';

@Component({
  selector: 'app-view-sertifikat',
  templateUrl: './view-sertifikat.component.html',
  styleUrls: ['./view-sertifikat.component.css']
})
export class ViewSertifikatComponent {
  alias=<string>{};
  sertifikat=<Sertifikat>{};
  ekstenzije = <Ekstenzije>{};
  closeResult = '';
  povlacenjeDugme = false;

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router,
    private route:ActivatedRoute,
    private fBuilder:FormBuilder,
    private modalService: NgbModal
  ){
    let temp=this.route.snapshot.paramMap.get('alias');
    if(temp != null)
        this.alias = temp;
    else
      this.alias = "nista";
  }

  ngOnInit():void{
    const item = sessionStorage.getItem('user');
		const jwt: JwtHelperService = new JwtHelperService();
		const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    if (info['uloga'] === "ROLE_MUSTERIJA"){
      this.sertifikatService.dajSertifikatMusterija(this.alias).subscribe(
        res=>{
          this.sertifikat=res;
          this.ekstenzije=this.sertifikat.ekstenzije;
        }
      )
    }else if (info['uloga'] === "ROLE_ADMIN"){
      this.sertifikatService.dajSertifikat(this.alias).subscribe(
        res=>{
          this.sertifikat=res;
          this.ekstenzije=this.sertifikat.ekstenzije;
          this.sertifikatService.povlacenjeDugme(this.alias).subscribe(
            res=>{
              this.povlacenjeDugme = res;
            }
          )
        }
      )
      
    }
  }


  povlacenjeSertifikata(){
    this.router.navigate(['/povlacenjeSertifikat/'+this.alias]);

  }

  proveriSertifikat(){
    this.sertifikatService.proveriSertifikat(this.sertifikat.alias).subscribe(
      res=>{
        alert("Sve je kako treba!");
      },error=>{
        alert("Sertifikat ne može da se koristi više!");
      }
    )
  }

  natrag(){
    this.router.navigate(['/viewAllSertifikat']);
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

}
