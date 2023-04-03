package Backend.AdminBackend.dto;

import Backend.AdminBackend.ostalo.KonverterDatum;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;

public class ZahtevZaSertifikatDTO {
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String namena;

    private String emailPotvrda;
    private Boolean potvrdjenZahtev;
    private Boolean prihvacen;
    private ZaKorisnikaDTO zaKorisnika;
    private ZaUredjajDTO zaUredjaj;
    private ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacija;

    public void konverzija(Object temp){
        LinkedHashMap<String, LinkedHashMap<String,Object>>lista = (LinkedHashMap<String, LinkedHashMap<String, Object>>)temp;
        LinkedHashMap<String,Object>lista2 = lista.get("zahtev");
        id = (Integer)lista2.get("id");
        //startDate =(Date) lista2.get("startDate");
        //endDate = (Date) lista2.get("endDate");
        //startDate -> 2023-04-12T22:56
        startDate = KonverterDatum.konvertovanjeSamoDatumUDateHitno(((String) lista2.get("startDate")).split("T")[0]);
        endDate = KonverterDatum.konvertovanjeSamoDatumUDateHitno(((String) lista2.get("endDate")).split("T")[0]);
        namena = (String) lista2.get("namena");
        emailPotvrda = (String) lista2.get("emailPotvrda");
        potvrdjenZahtev=(Boolean) lista2.get("potvrdjenZahtev");
        prihvacen = (Boolean) lista2.get("prihvacen");

        LinkedHashMap<String,String>lista3 =(LinkedHashMap<String, String>) lista2.get("zaKorisnika");
        zaKorisnika=new ZaKorisnikaDTO();
        if (lista3.size()!=0){
            zaKorisnika = new ZaKorisnikaDTO();
            if (lista3.get("id")!=null)
                zaKorisnika.setId(Integer.parseInt(lista3.get("id")));
            zaKorisnika.setEmail(lista3.get("email"));
            zaKorisnika.setIme(lista3.get("ime"));
            zaKorisnika.setPrezime(lista3.get("prezime"));
        }
        lista3 = (LinkedHashMap<String, String>) lista2.get("zaUredjaj");
        zaUredjaj = new ZaUredjajDTO();
        if (lista3.size()!=0){
            zaUredjaj = new ZaUredjajDTO();
            if (lista3.get("id")!=null)
                zaUredjaj.setId(Integer.parseInt(lista3.get("id")));
            zaUredjaj.setNaziv(lista3.get("naziv"));
            zaUredjaj.setSvrha(lista3.get("svrha"));
            zaUredjaj.setSerijskiBroj(lista3.get("serijskiBroj"));
        }
        lista3 = (LinkedHashMap<String, String>) lista2.get("zaMojaKucaAplikacija");
        zaMojaKucaAplikacija = new ZaMojaKucaAplikacijaDTO();
        if (lista3.size()!=0){
            zaMojaKucaAplikacija = new ZaMojaKucaAplikacijaDTO();
            if (lista3.get("id")!=null)
                zaMojaKucaAplikacija.setId(Integer.parseInt(lista3.get("id")));
            zaMojaKucaAplikacija.setSerijskiBroj(lista3.get("serijskiBroj"));
        }
    }

    public ZahtevZaSertifikatDTO() {
    }

    public ZahtevZaSertifikatDTO(Integer id, Date startDate, Date endDate, String namena, String emailPotvrda, Boolean potvrdjenZahtev, Boolean prihvacen, ZaKorisnikaDTO zaKorisnika, ZaUredjajDTO zaUredjaj, ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacija) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.namena = namena;
        this.emailPotvrda = emailPotvrda;
        this.potvrdjenZahtev = potvrdjenZahtev;
        this.prihvacen = prihvacen;
        this.zaKorisnika = zaKorisnika;
        this.zaUredjaj = zaUredjaj;
        this.zaMojaKucaAplikacija = zaMojaKucaAplikacija;
    }

    public String getEmailPotvrda() {
        return emailPotvrda;
    }

    public void setEmailPotvrda(String emailPotvrda) {
        this.emailPotvrda = emailPotvrda;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
