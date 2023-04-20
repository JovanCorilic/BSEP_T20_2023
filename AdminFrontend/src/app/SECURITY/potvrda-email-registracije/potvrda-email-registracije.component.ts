import { AuthenticationService } from 'src/app/SERVICE/authentication.service';
import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-potvrda-email-registracije',
  templateUrl: './potvrda-email-registracije.component.html',
  styleUrls: ['./potvrda-email-registracije.component.css']
})
export class PotvrdaEmailRegistracijeComponent {
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
    this.authenticationService.verifikacijaRegistracije(this.token).subscribe(
      res=>{
        this.router.navigate(['']);
      }
    )
  }
}
