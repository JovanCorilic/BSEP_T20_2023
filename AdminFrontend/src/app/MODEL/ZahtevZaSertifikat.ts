import { ZaMojaKucaAplikacija } from './ZaMojaKucaAplikacija';
import { ZaUredjaj } from './ZaUredjaj';
import { ZaKorisnika } from './ZaKorisnika';
export class ZahtevZaSertifikat {
    id: number;
    startDate: Date;
    endDate: Date;
    namena: string;
    emailPotvrda: string;
    potvrdjenZahtev: boolean;
    prihvacen: boolean;
    zaKorisnika: ZaKorisnika;
    zaUredjaj: ZaUredjaj;
    zaMojaKucaAplikacija: ZaMojaKucaAplikacija;
    constructor(
        id: number,
        startDate: Date,
        endDate: Date,
        namena: string,
        emailPotvrda: string,
        potvrdjenZahtev: boolean,
        prihvacen: boolean,
        zaKorisnika: ZaKorisnika,
        zaUredjaj: ZaUredjaj,
        zaMojaKucaAplikacija: ZaMojaKucaAplikacija
    ) {
        this.id = id
        this.startDate = startDate
        this.endDate = endDate
        this.namena = namena
        this.emailPotvrda = emailPotvrda
        this.potvrdjenZahtev = potvrdjenZahtev
        this.prihvacen = prihvacen
        this.zaKorisnika = zaKorisnika
        this.zaUredjaj = zaUredjaj
        this.zaMojaKucaAplikacija = zaMojaKucaAplikacija
    }
}