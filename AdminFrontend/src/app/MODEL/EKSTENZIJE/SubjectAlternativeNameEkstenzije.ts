import { AlternativeName } from "./AlternativeName"

export class SubjectAlternativeNameEkstenzije {
    daLiKoristi: boolean
    daLiJeKriticno: boolean
    alternativeNames: AlternativeName[]
    constructor(daLiKoristi: boolean, daLiJeKriticno: boolean, alternativeNames: AlternativeName[]) {
        this.daLiKoristi = daLiKoristi
        this.daLiJeKriticno = daLiJeKriticno
        this.alternativeNames = alternativeNames
    }
}