export class BasicConstraintsEkstenzije {
    daLiKoristi: boolean;

    isCritical: boolean;

    isCA: boolean;

    maxPathLen: number;
    constructor(
        daLiKoristi: boolean,
        isCritical: boolean,
        isCA: boolean,
        maxPathLen: number
    ) {
        this.daLiKoristi = daLiKoristi
        this.isCritical = isCritical
        this.isCA = isCA
        this.maxPathLen = maxPathLen
    }
}