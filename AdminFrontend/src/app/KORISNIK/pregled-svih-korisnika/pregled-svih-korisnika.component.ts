import { KorisnikService } from './../../SERVICE/korisnik.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { Korisnik } from 'src/app/MODEL/Korisnik';

@Component({
  selector: 'app-pregled-svih-korisnika',
  templateUrl: './pregled-svih-korisnika.component.html',
  styleUrls: ['./pregled-svih-korisnika.component.css']
})
export class PregledSvihKorisnikaComponent {
  lista:Korisnik[]|undefined;
  mapaIzgled:Map<string,{status:boolean,kliknuo:boolean}>;

  constructor(
    private korisnikService:KorisnikService,
    private router:Router
  ){
    this.mapaIzgled = new Map();
  }

  ngOnInit():void{
    this.korisnikService.sveMusterije().subscribe(
      res=>{
        this.lista = res;
        this.lista.forEach(element =>{
          this.mapaIzgled.set(element.email,{status : true, kliknuo : true});
        })
      }
    )
  }

  napraviMusterijuNalog(email:string){
    
    this.mapaIzgled.set(email,{status: false,kliknuo:true});
    this.korisnikService.napraviMusteriju(email).subscribe(
      res=>{
        this.mapaIzgled.set(email,{status: true,kliknuo:false});
      }
    );
  }

}
