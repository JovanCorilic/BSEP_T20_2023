package Backend.AdminBackend.dto;

import java.time.LocalDateTime;

public class ZahtevZaSertifikatDTO {
    private Integer id;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private String namena;
    private Boolean potvrdjenZahtev;
    private Boolean prihvacen;
    private ZaKorisnikaDTO zaKorisnika;
    private ZaUredjajDTO zaUredjaj;
    private ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacija;

    public ZahtevZaSertifikatDTO() {
    }

    public ZahtevZaSertifikatDTO(Integer id, LocalDateTime startDate, LocalDateTime endDate, String namena, Boolean potvrdjenZahtev, Boolean prihvacen, ZaKorisnikaDTO zaKorisnika, ZaUredjajDTO zaUredjaj, ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacija) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.namena = namena;
        this.potvrdjenZahtev = potvrdjenZahtev;
        this.prihvacen = prihvacen;
        this.zaKorisnika = zaKorisnika;
        this.zaUredjaj = zaUredjaj;
        this.zaMojaKucaAplikacija = zaMojaKucaAplikacija;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getNamena() {
        return namena;
    }

    public void setNamena(String namena) {
        this.namena = namena;
    }

    public Boolean getPotvrdjenZahtev() {
        return potvrdjenZahtev;
    }

    public void setPotvrdjenZahtev(Boolean potvrdjenZahtev) {
        this.potvrdjenZahtev = potvrdjenZahtev;
    }

    public Boolean getPrihvacen() {
        return prihvacen;
    }

    public void setPrihvacen(Boolean prihvacen) {
        this.prihvacen = prihvacen;
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
