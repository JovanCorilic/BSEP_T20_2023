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
    private router:Router
  ){}

  getRole():string{
    const item = localStorage.getItem('user');

    if(!item){
      return "";
    }

    const jwt:JwtHelperService = new JwtHelperService();
    const decodedItem = JSON.parse(item!);
    const info = jwt.decodeToken(decodedItem.accessToken);
    return info['roles'];
  }

  goToHome() {
    this.router.navigate(['']);
  }

  goToLogin(){
    this.router.navigate(['/login']);
  }

  goToCreateSertifikat(){
    this.router.navigate(['/createSertifikat']);
  }

}
