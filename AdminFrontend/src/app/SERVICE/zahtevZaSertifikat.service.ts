import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ZahtevZaSertifikat } from "../MODEL/ZahtevZaSertifikat";
import { Observable } from "rxjs";

@Injectable({
    providedIn:'root'
})
export class ZahtevZaSertifikatService{
    constructor(private http: HttpClient){}
    private path = "http://localhost:8080/zahtevZaSertifikat";

    public potvrdaZahtevaZaSertifikat(token:string){
        return this.http.put(this.path+"/potvrdaZahteva"+`/${token}`,null);
    }

    public createZahtevZaSertifikat(zahtev:any){
        return this.http.post(this.path+"/createZahtevZaSertifikat",zahtev);
    }

    public updateZahtevZaSertifikat(zahtev:ZahtevZaSertifikat){
        return this.http.put(this.path+"/updateZahtevZaSertifikat",zahtev); 
    }

    public dajZahtevZaSertifikat(id:number):Observable<ZahtevZaSertifikat>{
        return this.http.get<ZahtevZaSertifikat>(this.path+"/dajZahtevZaSertifikat"+`/${id}`);
    }

    public dajListuZahtevaZaSertifikat():Observable<ZahtevZaSertifikat[]>{
        return this.http.get<ZahtevZaSertifikat[]>(this.path+"/dajListuZahtevaZaSertifikat");
    }

    public dajListuZahtevaZaSertifikatMusterija():Observable<ZahtevZaSertifikat[]>{
        return this.http.get<ZahtevZaSertifikat[]>(this.path+"/dajListuZahtevaZaSertifikatMoji");
    }
}