import { AlternativeName } from "./AlternativeName"

export class SubjectAlternativeNameEkstenzije {
    daLiKoristi: boolean
    isCritical: boolean
    alternativeNames: AlternativeName[]
    constructor(daLiKoristi: boolean, isCritical: boolean, alternativeNames: AlternativeName[]) {
        this.daLiKoristi = daLiKoristi
        this.isCritical = isCritical
        this.alternativeNames = alternativeNames
    }
}