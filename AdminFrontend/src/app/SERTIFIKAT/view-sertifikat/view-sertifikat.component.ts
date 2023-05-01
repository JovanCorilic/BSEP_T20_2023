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
  ekstenzije = this.sertifikat.ekstenzije
  closeResult = '';

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
        }
      )
    }else if (info['uloga'] === "ROLE_ADMIN"){
      this.sertifikatService.dajSertifikat(this.alias).subscribe(
        res=>{
          this.sertifikat=res;
        }
      )
    }
  }

  povlacenjeSertifikata(){
    this.router.navigate(['/povlacenjeSertifikat/'+this.alias]);

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

}
