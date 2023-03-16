import { SertifikatService } from './../../SERVICE/sertifikat.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-create-sertifikat',
  templateUrl: './create-sertifikat.component.html',
  styleUrls: ['./create-sertifikat.component.css']
})
export class CreateSertifikatComponent {
  constructor(
    private sertifikatService:SertifikatService
  ){

  }

  ngOnInit(){
    this.napravi();
  }

  napravi(){
    this.sertifikatService.createSertifikat().subscribe(
      res=>{

      }
    )
  }
}
