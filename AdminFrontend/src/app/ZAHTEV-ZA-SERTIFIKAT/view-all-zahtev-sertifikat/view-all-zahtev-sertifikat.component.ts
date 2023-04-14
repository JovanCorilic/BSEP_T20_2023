import { Router } from '@angular/router';
import { SertifikatService } from '../../SERVICE/sertifikat.service';
import { ZahtevZaSertifikat } from '../../MODEL/ZahtevZaSertifikat';
import { Component } from '@angular/core';

@Component({
  selector: 'app-view-all-zahtev-sertifikat',
  templateUrl: './view-all-zahtev-sertifikat.component.html',
  styleUrls: ['./view-all-zahtev-sertifikat.component.css']
})
export class ViewAllZahtevSertifikatComponent {
  lista:ZahtevZaSertifikat[]|undefined;

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router
  ){

  }

  ngOnInit():void{
    this.sertifikatService.dajListuZahtevaZaSertifikat().subscribe(
      res=>{
        this.lista = res;
      }
    )
  }

  idiNaZahtev(id:number){
    this.router.navigate(['/viewZahtevZaSertifikat/'+id]);
  }
}
