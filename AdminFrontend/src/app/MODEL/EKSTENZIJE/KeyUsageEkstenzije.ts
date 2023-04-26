export class KeyUsageEkstenzije {
    daLiKoristi: boolean

    isCritical: boolean

    digitalSignature: boolean

    nonRepudiation: boolean

    keyEncipherment: boolean

    dataEncipherment: boolean

    keyAgreement: boolean

    keyCertSign: boolean

    cRLSign: boolean

    encipherOnly: boolean

    decipherOnly: boolean
    constructor(
        daLiKoristi: boolean,
        isCritical: boolean,
        digitalSignature: boolean,
        nonRepudiation: boolean,
        keyEncipherment: boolean,
        dataEncipherment: boolean,
        keyAgreement: boolean,
        keyCertSign: boolean,
        cRLSign: boolean,
        encipherOnly: boolean,
        decipherOnly: boolean
    ) {
        this.daLiKoristi = daLiKoristi
        this.isCritical = isCritical
        this.digitalSignature = digitalSignature
        this.nonRepudiation = nonRepudiation
        this.keyEncipherment = keyEncipherment
        this.dataEncipherment = dataEncipherment
        this.keyAgreement = keyAgreement
        this.keyCertSign = keyCertSign
        this.cRLSign = cRLSign
        this.encipherOnly = encipherOnly
        this.decipherOnly = decipherOnly
    }
}