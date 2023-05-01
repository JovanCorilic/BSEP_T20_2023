package Backend.AdminBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ZahtevZaSertifikat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Date startDate;
    @Column
    private Date endDate;

    @Column
    private String namena;

    @Column
    private String emailPotvrda;

    @Column
    private String organizacionaJedinica;

    @Column
    private String nazivOrganizacije;

    @Column
    private String skraceniNazivZemlje;

    @Column
    private Boolean potvrdjenZahtev;

    @Column
    private Boolean prihvacen;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private ZaKorisnika zaKorisnika;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private ZaUredjaj zaUredjaj;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private ZaMojaKucaAplikacija zaMojaKucaAplikacija;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private Korisnik musterija;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private Korisnik admin;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private Ekstenzije ekstenzije;

    public void Update(ZahtevZaSertifikat zahtevZaSertifikat){
        this.startDate = zahtevZaSertifikat.getStartDate();
        this.endDate = zahtevZaSertifikat.getEndDate();
        this.namena = zahtevZaSertifikat.getNamena();
        this.emailPotvrda = zahtevZaSertifikat.getEmailPotvrda();
        this.organizacionaJedinica = zahtevZaSertifikat.getOrganizacionaJedinica();
        this.nazivOrganizacije = zahtevZaSertifikat.getNazivOrganizacije();
        this.skraceniNazivZemlje = zahtevZaSertifikat.getSkraceniNazivZemlje();
        //this.potvrdjenZahtev = zahtevZaSertifikat.getPotvrdjenZahtev();
        //this.prihvacen=zahtevZaSertifikat.getPrihvacen();
        if (zahtevZaSertifikat.getZaKorisnika() !=null)
            this.zaKorisnika.Update(zahtevZaSertifikat.getZaKorisnika());
        if (zahtevZaSertifikat.getZaUredjaj() != null)
            this.zaUredjaj.Update(zahtevZaSertifikat.getZaUredjaj());
        if (zahtevZaSertifikat.getZaMojaKucaAplikacija() != null)
            this.zaMojaKucaAplikacija.Update(zahtevZaSertifikat.getZaMojaKucaAplikacija());
    }

}
