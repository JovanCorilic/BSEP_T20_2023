import { Ekstenzije } from "./Ekstenzije";
import { Korisnik } from "./Korisnik";
import { ZaKorisnika } from "./ZaKorisnika";
import { ZaMojaKucaAplikacija } from "./ZaMojaKucaAplikacija";
import { ZaUredjaj } from "./ZaUredjaj";

export class Sertifikat {
    alias: string
    namena: string
    startDate: Date
    endDate: Date
    subjectEmail: string

    organizacionaJedinica: string
    nazivOrganizacije: string
    skraceniNazivZemlje: string

    zaKorisnika: ZaKorisnika
    zaUredjaj: ZaUredjaj
    zaMojaKucaAplikacija: ZaMojaKucaAplikacija
    musterija: Korisnik
    admin: Korisnik
    ekstenzije: Ekstenzije
    constructor(
        alias: string,
        namena: string,
        startDate: Date,
        endDate: Date,
        subjectEmail: string,
        organizacionaJedinica: string,
        nazivOrganizacije: string,
        skraceniNazivZemlje: string,
        zaKorisnika: ZaKorisnika,
        zaUredjaj: ZaUredjaj,
        zaMojaKucaAplikacija: ZaMojaKucaAplikacija,
        musterija: Korisnik,
        admin: Korisnik,
        ekstenzije: Ekstenzije
    ) {
        this.alias = alias
        this.namena = namena
        this.startDate = startDate
        this.endDate = endDate
        this.subjectEmail = subjectEmail
        this.organizacionaJedinica = organizacionaJedinica
        this.nazivOrganizacije = nazivOrganizacije
        this.skraceniNazivZemlje = skraceniNazivZemlje
        this.zaKorisnika = zaKorisnika
        this.zaUredjaj = zaUredjaj
        this.zaMojaKucaAplikacija = zaMojaKucaAplikacija
        this.musterija = musterija
        this.admin = admin
        this.ekstenzije = ekstenzije
    }

}