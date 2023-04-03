import { Korisnik } from "./Korisnik";
import { ZaKorisnika } from "./ZaKorisnika";
import { ZaMojaKucaAplikacija } from "./ZaMojaKucaAplikacija";
import { ZaUredjaj } from "./ZaUredjaj";

export class Sertifikat {
    alias: string;
    namena: string;
    startDate: Date;
    endDate: Date;
    subjectEmail: string;
    korisnik: Korisnik;
    zaKorisnika: ZaKorisnika;
    zaUredjaj: ZaUredjaj;
    zaMojaKucaAplikacija: ZaMojaKucaAplikacija;
    constructor(
        alias: string,
        namena: string,
        startDate: Date,
        endDate: Date,
        subjectEmail: string,
        korisnik: Korisnik,
        zaKorisnika: ZaKorisnika,
        zaUredjaj: ZaUredjaj,
        zaMojaKucaAplikacija: ZaMojaKucaAplikacija
    ) {
        this.alias = alias
        this.namena = namena
        this.startDate = startDate
        this.endDate = endDate
        this.subjectEmail = subjectEmail
        this.korisnik = korisnik
        this.zaKorisnika = zaKorisnika
        this.zaUredjaj = zaUredjaj
        this.zaMojaKucaAplikacija = zaMojaKucaAplikacija
    }
}