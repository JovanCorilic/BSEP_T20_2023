package Backend.AdminBackend.dto;

import java.util.Date;

public class SertifikatDTO {
    private String alias;
    private String namena;
    private Date startDate;
    private Date endDate;
    private  String subjectEmail;
    private KorisnikDTO korisnik;
    private ZaKorisnikaDTO zaKorisnika;
    private ZaUredjajDTO zaUredjaj;
    private ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacija;

    public SertifikatDTO(String alias, String namena, Date startDate, Date endDate, String subjectEmail, KorisnikDTO korisnik, ZaKorisnikaDTO zaKorisnika, ZaUredjajDTO zaUredjaj, ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacija) {
        this.alias = alias;
        this.namena = namena;
        this.startDate = startDate;
        this.endDate = endDate;
        this.subjectEmail = subjectEmail;
        this.korisnik = korisnik;
        this.zaKorisnika = zaKorisnika;
        this.zaUredjaj = zaUredjaj;
        this.zaMojaKucaAplikacija = zaMojaKucaAplikacija;
    }

    public SertifikatDTO() {
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getNamena() {
        return namena;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSubjectEmail() {
        return subjectEmail;
    }

    public void setSubjectEmail(String subjectEmail) {
        this.subjectEmail = subjectEmail;
    }

    public KorisnikDTO getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(KorisnikDTO korisnik) {
        this.korisnik = korisnik;
    }

    public ZaKorisnikaDTO getZaKorisnika() {
        return zaKorisnika;
    }

    public void setZaKorisnika(ZaKorisnikaDTO zaKorisnika) {
        this.zaKorisnika = zaKorisnika;
    }

    public ZaUredjajDTO getZaUredjaj() {
        return zaUredjaj;
    }

    public void setZaUredjaj(ZaUredjajDTO zaUredjaj) {
        this.zaUredjaj = zaUredjaj;
    }

    public ZaMojaKucaAplikacijaDTO getZaMojaKucaAplikacija() {
        return zaMojaKucaAplikacija;
    }

    public void setZaMojaKucaAplikacija(ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacija) {
        this.zaMojaKucaAplikacija = zaMojaKucaAplikacija;
    }
}
