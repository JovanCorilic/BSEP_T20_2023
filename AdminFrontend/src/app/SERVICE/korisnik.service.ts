import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Musterija } from "../MODEL/Musterija";
import { Korisnik } from "../MODEL/Korisnik";

@Injectable({
    providedIn:'root'
})
export class KorisnikService{
    constructor(private http: HttpClient){}
    private path = "http://localhost:8080/korisnik";

    sveMusterije():Observable<Korisnik[]>{
        return this.http.get<Korisnik[]>(this.path+"/sveMusterije");
    }

    pravljenjeAdmina(admin:Musterija){
        return this.http.post(this.path+"/napraviAdmina",admin);
    }

    napraviMusteriju(email:string){
        return this.http.get(this.path+"/napraviMusteriju"+`/${email}`);
    }

    
}