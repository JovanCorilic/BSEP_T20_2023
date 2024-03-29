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
    private path = "https://localhost:8443/sertifikat";

    public dajSvePovuceneSertifikate():Observable<PovlacenjeSertifikata[]>{
        return this.http.get<PovlacenjeSertifikata[]>(this.path+"/dajSvePovuceneSertifikate");
    }

    public povuciSertifikat(povuci:PovlacenjeSertifikata){
        return this.http.post(this.path+"/povuciSertifikat",povuci);
    }

    public dajSveSertifikate():Observable<Sertifikat[]>{
        return this.http.get<Sertifikat[]>(this.path+"/dajSveSertifikate");
    }

    public dajSveSertifikateMusterije():Observable<Sertifikat[]>{
        return this.http.get<Sertifikat[]>(this.path+"/dajSveSertifikateMusterija");
    }

    public dajSertifikat(alias:string):Observable<Sertifikat>{
        return this.http.get<Sertifikat>(this.path+"/dajSertifikat"+`/${alias}`);
    }

    public dajSertifikatMusterija(alias:string):Observable<Sertifikat>{
        return this.http.get<Sertifikat>(this.path+"/dajSertifikatMusterija"+`/${alias}`);
    }
    
    public createSertifikat(zahtev:ZahtevZaSertifikat){
        return this.http.post(this.path+"/napravi",zahtev);
    }

    public proveriSertifikat(alias:string){
        return this.http.get(this.path+"/proveriSertifikat"+`/${alias}`);
    }

    public povlacenjeDugme(alias:string):Observable<boolean>{
        return this.http.get<boolean>(this.path+"/povlacenjeDugme"+`/${alias}`);
    }
    
}