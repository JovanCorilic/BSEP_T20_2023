import { AuthorityKeyIdentifierEkstenzije } from "./EKSTENZIJE/AuthorityKeyIdentifierEkstenzije";
import { BasicConstraintsEkstenzije } from "./EKSTENZIJE/BasicConstraintsEkstenzije";
import { ExtendedKeyUsageEkstenzije } from "./EKSTENZIJE/ExtendedKeyUsageEkstenzije";
import { KeyUsageEkstenzije } from "./EKSTENZIJE/KeyUsageEkstenzije";
import { SubjectAlternativeNameEkstenzije } from "./EKSTENZIJE/SubjectAlternativeNameEkstenzije";
import { SubjectKeyIdentifierEkstenzije } from "./EKSTENZIJE/SubjectKeyIdentifierEkstenzije";

export class Ekstenzije {
    authorityKeyIdentifierEkstenzije: AuthorityKeyIdentifierEkstenzije

    basicConstraintsEkstenzije: BasicConstraintsEkstenzije

    extendedKeyUsageEkstenzije: ExtendedKeyUsageEkstenzije

    keyUsageEkstenzije: KeyUsageEkstenzije

    subjectAlternativeNameEkstenzije: SubjectAlternativeNameEkstenzije

    subjectKeyIdentifierEkstenzije: SubjectKeyIdentifierEkstenzije
    constructor(
        authorityKeyIdentifierEkstenzije: AuthorityKeyIdentifierEkstenzije,
        basicConstraintsEkstenzije: BasicConstraintsEkstenzije,
        extendedKeyUsageEkstenzije: ExtendedKeyUsageEkstenzije,
        keyUsageEkstenzije: KeyUsageEkstenzije,
        subjectAlternativeNameEkstenzije: SubjectAlternativeNameEkstenzije,
        subjectKeyIdentifierEkstenzije: SubjectKeyIdentifierEkstenzije
    ) {
        this.authorityKeyIdentifierEkstenzije = authorityKeyIdentifierEkstenzije
        this.basicConstraintsEkstenzije = basicConstraintsEkstenzije
        this.extendedKeyUsageEkstenzije = extendedKeyUsageEkstenzije
        this.keyUsageEkstenzije = keyUsageEkstenzije
        this.subjectAlternativeNameEkstenzije = subjectAlternativeNameEkstenzije
        this.subjectKeyIdentifierEkstenzije = subjectKeyIdentifierEkstenzije
    }
}