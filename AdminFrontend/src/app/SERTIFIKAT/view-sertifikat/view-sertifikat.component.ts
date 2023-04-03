import { Component } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
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
    this.sertifikatService.dajSertifikat(this.alias).subscribe(
      res=>{
        this.sertifikat=res;
      }
    )
  }

  povlacenjeSertifikata(){
    this.router.navigate(['/povlacenjeSertifikat/'+this.alias]);

  }

  natrag(){
    this.router.navigate(['/viewAllSertifikat']);
  }

}
