import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { Sertifikat } from 'src/app/MODEL/Sertifikat';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';

@Component({
  selector: 'app-view-all-sertifikat',
  templateUrl: './view-all-sertifikat.component.html',
  styleUrls: ['./view-all-sertifikat.component.css']
})
export class ViewAllSertifikatComponent {
  lista:Sertifikat[]|undefined;

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router
  ){

  }

  ngOnInit():void{
    const item = localStorage.getItem('user');
		const jwt: JwtHelperService = new JwtHelperService();
		const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    if (info['uloga'] === "ROLE_MUSTERIJA"){
      this.sertifikatService.dajSveSertifikateMusterije().subscribe(
        res=>{
          this.lista = res;
        }
      )
    }else if (info['uloga'] === "ROLE_ADMIN"){
      this.sertifikatService.dajSveSertifikate().subscribe(
        res=>{
          this.lista = res;
        }
      )
    }
  }

  idiNaZahtev(alias:string){
    this.router.navigate(['/viewSertifikat/'+alias]);
  }

}
