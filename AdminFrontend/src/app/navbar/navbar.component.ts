import { AuthenticationService } from './../SERVICE/authentication.service';
import { Router } from '@angular/router';
import { Component } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {

  constructor(
    private router:Router,
    private authenticationService:AuthenticationService
  ){}

  getRole():string{
    const item = sessionStorage.getItem('user');

    if(!item){
      return "";
    }

    const jwt:JwtHelperService = new JwtHelperService();
    const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    return info['uloga'];
  }

  goToHome() {
    this.router.navigate(['']);
  }

  goToLogin(){
    this.router.navigate(['/login']);
  }

  goToCreateZahtevZaSertifikat(){
    this.router.navigate(['/createZahtevZaSertifikat']);
  }

  goToSviZahteviSertifikat(){
    this.router.navigate(['/viewAllZahtevSertifikat']);
  }

  goToSviSertifikati(){
    this.router.navigate(['/viewAllSertifikat']);
  }

  goToSviPovuceniSertifikati(){
    this.router.navigate(['/povuceniSertifikati']);
  }

  goToZahteviZaRegistraciju(){
    this.router.navigate(['/pregledSvihKorisnika']);
  }

  goToPravljenjeAdmina(){
    this.router.navigate(['/pravljenjeAdmina']);
  }

  goToRegister(){
    this.router.navigate(['/register']);
  }

  logOut() {
    this.authenticationService.logout().subscribe(
			result => {
				
			}
		);
    sessionStorage.removeItem('user');
    sessionStorage.removeItem('accessToken');
    sessionStorage.removeItem('uloga');
    this.router.navigate(['']);
  }

}
