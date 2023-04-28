export class KeyUsageEkstenzije {
    daLiKoristi: boolean

    daLiJeKriticno: boolean

    digitalSignature: boolean

    nonRepudiation: boolean

    keyEncipherment: boolean

    dataEncipherment: boolean

    keyAgreement: boolean

    keyCertSign: boolean

    da_li_jecrlsign: boolean

    encipherOnly: boolean

    decipherOnly: boolean
    constructor(
        daLiKoristi: boolean,
        daLiJeKriticno: boolean,
        digitalSignature: boolean,
        nonRepudiation: boolean,
        keyEncipherment: boolean,
        dataEncipherment: boolean,
        keyAgreement: boolean,
        keyCertSign: boolean,
        da_li_jecrlsign: boolean,
        encipherOnly: boolean,
        decipherOnly: boolean
    ) {
        this.daLiKoristi = daLiKoristi
        this.daLiJeKriticno = daLiJeKriticno
        this.digitalSignature = digitalSignature
        this.nonRepudiation = nonRepudiation
        this.keyEncipherment = keyEncipherment
        this.dataEncipherment = dataEncipherment
        this.keyAgreement = keyAgreement
        this.keyCertSign = keyCertSign
        this.da_li_jecrlsign = da_li_jecrlsign
        this.encipherOnly = encipherOnly
        this.decipherOnly = decipherOnly
    }
}