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
        return this.http.post(this.path+"/create",null);
    }


}