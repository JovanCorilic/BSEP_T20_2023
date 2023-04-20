import { ZahtevZaSertifikat } from './../MODEL/ZahtevZaSertifikat';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Sertifikat } from '../MODEL/Sertifikat';
import { PovlacenjeSertifikata } from '../MODEL/PovlacenjeSertifikata';

@Injectable({
    providedIn:'root'
})
export class SertifikatService{
    constructor(private http: HttpClient){}
    private path = "http://localhost:8080/sertifikat";

    public dajSvePovuceneSertifikate():Observable<PovlacenjeSertifikata[]>{
        return this.http.get<PovlacenjeSertifikata[]>(this.path+"/dajSvePovuceneSertifikate");
    }

    public povuciSertifikat(povuci:PovlacenjeSertifikata){
        return this.http.post(this.path+"/povuciSertifikat",povuci);
    }

    public dajSveSertifikate():Observable<Sertifikat[]>{
        return this.http.get<Sertifikat[]>(this.path+"/dajSveSertifikate");
    }

    public dajSertifikat(alias:string):Observable<Sertifikat>{
        return this.http.get<Sertifikat>(this.path+"/dajSertifikat"+`/${alias}`);
    }
    
    public createSertifikat(zahtev:ZahtevZaSertifikat){
        return this.http.post(this.path+"/napravi",zahtev);
    }
    
}