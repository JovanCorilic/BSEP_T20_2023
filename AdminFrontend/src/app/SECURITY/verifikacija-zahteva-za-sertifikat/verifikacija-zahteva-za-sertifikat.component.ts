import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';

@Component({
  selector: 'app-verifikacija-zahteva-za-sertifikat',
  templateUrl: './verifikacija-zahteva-za-sertifikat.component.html',
  styleUrls: ['./verifikacija-zahteva-za-sertifikat.component.css']
})
export class VerifikacijaZahtevaZaSertifikatComponent {
  token = <string>{};
  status: boolean = true;

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router,
    private route:ActivatedRoute,
  ){
    let temp=this.route.snapshot.paramMap.get('token');
        if(temp != null)
            this.token = temp;
        else
          this.token = "nista";
  }

  potvrdiZahtev(){
    this.status = !this.status; 
    this.sertifikatService.potvrdaZahtevaZaSertifikat(this.token).subscribe(
      res=>{
        this.router.navigate(['/viewAllZahtevSertifikat']);
      }
    )
  }
}
