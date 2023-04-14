import { Component } from '@angular/core';
import { Router } from '@angular/router';
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
    this.sertifikatService.dajSveSertifikate().subscribe(
      res=>{
        this.lista = res;
      }
    )
  }

  idiNaZahtev(alias:string){
    this.router.navigate(['/viewSertifikat/'+alias]);
  }

}
