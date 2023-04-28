import { Router } from '@angular/router';
import { SertifikatService } from '../../SERVICE/sertifikat.service';
import { ZahtevZaSertifikat } from '../../MODEL/ZahtevZaSertifikat';
import { Component } from '@angular/core';
import { ZahtevZaSertifikatService } from 'src/app/SERVICE/zahtevZaSertifikat.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-view-all-zahtev-sertifikat',
  templateUrl: './view-all-zahtev-sertifikat.component.html',
  styleUrls: ['./view-all-zahtev-sertifikat.component.css']
})
export class ViewAllZahtevSertifikatComponent {
  lista:ZahtevZaSertifikat[]|undefined;

  constructor(
    private sertifikatService:SertifikatService,
    private zahtevZaSertifikatService:ZahtevZaSertifikatService,
    private router:Router
  ){

  }

  ngOnInit():void{
    const item = localStorage.getItem('user');
		const jwt: JwtHelperService = new JwtHelperService();
		const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    if (info['uloga'] === "ROLE_MUSTERIJA"){
      this.zahtevZaSertifikatService.dajListuZahtevaZaSertifikatMusterija().subscribe(
        res=>{
          this.lista = res;
        }
      )
    }else if(info['uloga'] === "ROLE_ADMIN"){
      this.zahtevZaSertifikatService.dajListuZahtevaZaSertifikat().subscribe(
        res=>{
          this.lista = res;
        }
      )
    }
  }

  idiNaZahtev(id:number){
    this.router.navigate(['/viewZahtevZaSertifikat/'+id]);
  }
}
