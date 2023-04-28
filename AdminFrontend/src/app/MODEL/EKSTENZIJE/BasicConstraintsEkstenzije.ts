export class BasicConstraintsEkstenzije {
    daLiKoristi: boolean;

    daLiJeKriticno: boolean;

    daLiJeCA: boolean;

    maxPathLen: number;
    constructor(
        daLiKoristi: boolean,
        daLiJeKriticno: boolean,
        daLiJeCA: boolean,
        maxPathLen: number
    ) {
        this.daLiKoristi = daLiKoristi
        this.daLiJeKriticno = daLiJeKriticno
        this.daLiJeCA = daLiJeCA
        this.maxPathLen = maxPathLen
    }
}