import { Router } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SertifikatService } from './../../SERVICE/sertifikat.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-create-sertifikat',
  templateUrl: './create-sertifikat.component.html',
  styleUrls: ['./create-sertifikat.component.css']
})
export class CreateSertifikatComponent {
  createForm: FormGroup;

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router,
    private fBuilder:FormBuilder
  ){
    this.createForm = this.fBuilder.group({

    })
  }

  ngOnInit(){

  }

  napravi(){
    this.sertifikatService.createSertifikat();
  }
}
