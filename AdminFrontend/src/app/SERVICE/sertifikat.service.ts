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

    public povuciSertifikat(povuci:PovlacenjeSertifikata):Observable<any>{
        return this.http.post(this.path+"/povuciSertifikat",povuci);
    }

    public dajSveSertifikate():Observable<Sertifikat[]>{
        return this.http.get<Sertifikat[]>(this.path+"/dajSveSertifikate");
    }

    public dajSertifikat(alias:string):Observable<Sertifikat>{
        return this.http.get<Sertifikat>(this.path+"/dajSertifikat"+`/${alias}`);
    }
    
    public createSertifikat(zahtev:ZahtevZaSertifikat):Observable<any>{
        return this.http.post(this.path+"/napravi",zahtev);
    }

    public createZahtevZaSertifikat(zahtev:ZahtevZaSertifikat):Observable<any>{
        console.log(zahtev);
        return this.http.post(this.path+"/createZahtevZaSertifikat",{zahtev});
    }

    public updateZahtevZaSertifikat(zahtev:ZahtevZaSertifikat):Observable<any>{
        return this.http.put(this.path+"/updateZahtevZaSertifikat",{zahtev}); 
    }

    public dajZahtevZaSertifikat(id:number):Observable<ZahtevZaSertifikat>{
        return this.http.get<ZahtevZaSertifikat>(this.path+"/dajZahtevZaSertifikat"+`/${id}`);
    }

    public dajListuZahtevaZaSertifikat():Observable<ZahtevZaSertifikat[]>{
        return this.http.get<ZahtevZaSertifikat[]>(this.path+"/dajListuZahtevaZaSertifikat");
    }

    public izbrisiZahtevZaSertifikat(id:number){
        return this.http.delete(this.path+"/izbrisiZahtevZaSertifikat"+`/${id}`);
    }
}