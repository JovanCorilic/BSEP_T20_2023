import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { PovlacenjeSertifikata } from 'src/app/MODEL/PovlacenjeSertifikata';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';

@Component({
  selector: 'app-view-all-povuceni-sertifikati',
  templateUrl: './view-all-povuceni-sertifikati.component.html',
  styleUrls: ['./view-all-povuceni-sertifikati.component.css']
})
export class ViewAllPovuceniSertifikatiComponent {
  lista:PovlacenjeSertifikata[]|undefined;
  constructor(
    private sertifikatService:SertifikatService,
    private router:Router
  ){

  }

  ngOnInit():void{
    this.sertifikatService.dajSvePovuceneSertifikate().subscribe(
      res=>{
        this.lista = res;
      }
    )
  }
}
