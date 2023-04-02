import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
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
      startDate: ["",[Validators.required]],
      endDate: ["",[Validators.required]],
      namena: ["",[Validators.required]],
      emailPotvrda: ["",[Validators.required]],

      email: ["",[Validators.required]],
      ime: ["",[Validators.required]],
      prezime: ["",[Validators.required]],

      serijskiBroj: ["",[Validators.required]],

      naziv: ["",[Validators.required]],
      svrha: ["",[Validators.required]],
      //serijskiBroj: ["",[Validators.required]]
    })
  }

  ngOnInit(){

  }

  napravi(){
    this.sertifikatService.createSertifikat();
  }
}
