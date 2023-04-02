export class ZaUredjaj {
    id: number;
    naziv: string;
    svrha: string;
    serijskiBroj: string;
    constructor(
        id: number,
        naziv: string,
        svrha: string,
        serijskiBroj: string
    ) {
        this.id = id
        this.naziv = naziv
        this.svrha = svrha
        this.serijskiBroj = serijskiBroj
    }
}