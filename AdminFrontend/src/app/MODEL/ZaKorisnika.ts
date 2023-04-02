export class ZaKorisnika {
    id: number;
    email: string;
    ime: string;
    prezime: string;
    constructor(
        id: number,
        email: string,
        ime: string,
        prezime: string
    ) {
        this.id = id
        this.email = email
        this.ime = ime
        this.prezime = prezime
    }
}