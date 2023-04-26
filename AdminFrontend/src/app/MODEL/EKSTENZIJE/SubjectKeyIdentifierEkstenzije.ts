export class SubjectKeyIdentifierEkstenzije {
    daLiKoristi: boolean

    isCritical: boolean
    constructor(daLiKoristi: boolean, isCritical: boolean) {
        this.daLiKoristi = daLiKoristi
        this.isCritical = isCritical
    }
}