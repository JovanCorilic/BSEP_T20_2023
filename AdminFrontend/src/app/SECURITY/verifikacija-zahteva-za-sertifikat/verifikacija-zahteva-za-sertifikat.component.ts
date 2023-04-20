import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';
import { ZahtevZaSertifikatService } from 'src/app/SERVICE/zahtevZaSertifikat.service';

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
    private zahtevZaSertifikatService:ZahtevZaSertifikatService,
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
    this.zahtevZaSertifikatService.potvrdaZahtevaZaSertifikat(this.token).subscribe(
      res=>{
        this.router.navigate(['/viewAllZahtevSertifikat']);
      }
    )
  }
}
