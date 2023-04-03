export class ZahtevZaSertifikatShort {
    id: number;
    startDate: Date;
    endDate: Date;
    namena: string;

    emailPotvrda: string;
    potvrdjenZahtev: string;
    prihvacen: string;
    constructor(
        id: number,
        startDate: Date,
        endDate: Date,
        namena: string,
        emailPotvrda: string,
        potvrdjenZahtev: string,
        prihvacen: string
    ) {
        this.id = id
        this.startDate = startDate
        this.endDate = endDate
        this.namena = namena
        this.emailPotvrda = emailPotvrda
        this.potvrdjenZahtev = potvrdjenZahtev
        this.prihvacen = prihvacen
    }
}