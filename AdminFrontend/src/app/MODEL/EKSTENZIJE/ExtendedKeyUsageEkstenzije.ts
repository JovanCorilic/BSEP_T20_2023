export class ExtendedKeyUsageEkstenzije {
    daLiKoristi: boolean

    daLiJeKriticno: boolean

    anyExtendedKeyUsage: boolean

    //Code signing

    id_kp_codeSigning: boolean
    //Email protection

    id_kp_emailProtection: boolean
    //IP Security End System
    id_kp_ipsecEndSystem: boolean
    //IP Security Tunnel Termination ????
    //private boolean id_kp_ipsecTunnel;
    //IP Security User
    id_kp_ipsecUser: boolean
    //Time Stamping
    id_kp_timeStamping: boolean
    //OCSP Signing
    id_kp_OCSPSigning: boolean
    //Smartcard logon
    id_kp_smartcardlogon: boolean
    constructor(
        daLiKoristi: boolean,
        daLiJeKriticno: boolean,
        anyExtendedKeyUsage: boolean,
        id_kp_codeSigning: boolean,
        id_kp_emailProtection: boolean,
        id_kp_ipsecEndSystem: boolean,
        id_kp_ipsecUser: boolean,
        id_kp_timeStamping: boolean,
        id_kp_OCSPSigning: boolean,
        id_kp_smartcardlogon: boolean
    ) {
        this.daLiKoristi = daLiKoristi
        this.daLiJeKriticno = daLiJeKriticno
        this.anyExtendedKeyUsage = anyExtendedKeyUsage
        this.id_kp_codeSigning = id_kp_codeSigning
        this.id_kp_emailProtection = id_kp_emailProtection
        this.id_kp_ipsecEndSystem = id_kp_ipsecEndSystem
        this.id_kp_ipsecUser = id_kp_ipsecUser
        this.id_kp_timeStamping = id_kp_timeStamping
        this.id_kp_OCSPSigning = id_kp_OCSPSigning
        this.id_kp_smartcardlogon = id_kp_smartcardlogon
    }
}