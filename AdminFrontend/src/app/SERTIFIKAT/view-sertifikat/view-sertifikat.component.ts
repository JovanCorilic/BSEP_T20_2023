import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
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

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router,
    private route:ActivatedRoute,
    private fBuilder:FormBuilder
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

}
