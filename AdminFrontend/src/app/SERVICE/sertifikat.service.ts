import { ZahtevZaSertifikat } from './../MODEL/ZahtevZaSertifikat';
import { HttpClient, HttpHeaders } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn:'root'
})
export class SertifikatService{
    constructor(private http: HttpClient){}
    private path = "http://localhost:8080/sertifikat";

    public createSertifikat(){
        this.http.post(this.path+"/create",null);
    }

    public createZahtevZaSertifikat(zahtev:ZahtevZaSertifikat){
        this.http.post(this.path+"/createZahtevZaSertifikat",{zahtev});
    }

    public updateZahtevZaSertifikat(zahtev:ZahtevZaSertifikat){
        this.http.put(this.path+"/updateZahtevZaSertifikat",{zahtev}); 
    }

    public dajZahtevZaSertifikat(id:number):Observable<ZahtevZaSertifikat>{
        return this.http.get<ZahtevZaSertifikat>(this.path+"/dajZahtevZaSertifikat"+`/${id}`);
    }

    public dajListuZahtevaZaSertifikat():Observable<ZahtevZaSertifikat[]>{
        return this.http.get<ZahtevZaSertifikat[]>(this.path+"/dajListuZahtevaZaSertifikat");
    }

    public izbrisiZahtevZaSertifikat(id:number){
        this.http.delete(this.path+"/izbrisiZahtevZaSertifikat"+`/${id}`);
    }
}