package Backend.AdminBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

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
    private LocalDateTime startDate;
    @Column
    private LocalDateTime endDate;

    @Column
    private String namena;

    @Column
    private String emailPotvrda;

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

    public void Update(ZahtevZaSertifikat zahtevZaSertifikat){
        this.startDate = zahtevZaSertifikat.getStartDate();
        this.endDate = zahtevZaSertifikat.getEndDate();
        this.namena = zahtevZaSertifikat.getNamena();
        this.emailPotvrda = zahtevZaSertifikat.getEmailPotvrda();
        this.potvrdjenZahtev = zahtevZaSertifikat.getPotvrdjenZahtev();
        this.prihvacen=zahtevZaSertifikat.getPrihvacen();
        this.zaKorisnika=zahtevZaSertifikat.getZaKorisnika();
        this.zaUredjaj=zahtevZaSertifikat.getZaUredjaj();
        this.zaMojaKucaAplikacija=zahtevZaSertifikat.getZaMojaKucaAplikacija();
    }

}
