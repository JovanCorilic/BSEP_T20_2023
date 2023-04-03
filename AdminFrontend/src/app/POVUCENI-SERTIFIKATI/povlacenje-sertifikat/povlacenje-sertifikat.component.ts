import { Component } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PovlacenjeSertifikata } from 'src/app/MODEL/PovlacenjeSertifikata';
import { SertifikatService } from 'src/app/SERVICE/sertifikat.service';

@Component({
  selector: 'app-povlacenje-sertifikat',
  templateUrl: './povlacenje-sertifikat.component.html',
  styleUrls: ['./povlacenje-sertifikat.component.css']
})
export class PovlacenjeSertifikatComponent {
  id=<string>{};

  constructor(
    private sertifikatService:SertifikatService,
    private router:Router,
    private route:ActivatedRoute
  ) {
    let temp=this.route.snapshot.paramMap.get('alias');
    if(temp != null)
        this.id = temp;
    else
      this.id = "nista";
   }

  ngOnInit(): void {
    this.loadJS()
  }

  posalji(){
    let razlog = (document.getElementById("text") as HTMLTextAreaElement).value;
    this.sertifikatService.povuciSertifikat(new PovlacenjeSertifikata(razlog,this.id));
    this.router.navigate(['/viewSertifikat/'+this.id])
  }

  loadJS(){
    let el = document.createElement('script');
    el.setAttribute('src', 'assets/povlacenje-sertifikata.js')
    document.body.appendChild(el);  
  }

  natrag(){
    this.router.navigate(['/viewSertifikat/'+this.id])
  }

}
