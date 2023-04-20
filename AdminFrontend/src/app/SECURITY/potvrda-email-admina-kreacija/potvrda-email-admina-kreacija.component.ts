import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthenticationService } from 'src/app/SERVICE/authentication.service';

@Component({
  selector: 'app-potvrda-email-admina-kreacija',
  templateUrl: './potvrda-email-admina-kreacija.component.html',
  styleUrls: ['./potvrda-email-admina-kreacija.component.css']
})
export class PotvrdaEmailAdminaKreacijaComponent {
  token = <string>{};
  status: boolean = true;

  constructor(
    private authenticationService:AuthenticationService,
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
    this.authenticationService.verifikacijaAdminNalog(this.token).subscribe(
      res=>{
        this.router.navigate(['']);
      }
    )
  }
}
