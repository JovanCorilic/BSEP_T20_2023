export class SertifikatSimple {
    alias: string;
    namena: string;
    startDate: Date;
    endDate: Date;
    subjectEmail: string;
    adminEmail: string;
    constructor(
        alias: string,
        namena: string,
        startDate: Date,
        endDate: Date,
        subjectEmail: string,
        adminEmail: string
    ) {
        this.alias = alias
        this.namena = namena
        this.startDate = startDate
        this.endDate = endDate
        this.subjectEmail = subjectEmail
        this.adminEmail = adminEmail
    }
}