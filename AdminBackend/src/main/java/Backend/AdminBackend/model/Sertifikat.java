package Backend.AdminBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.PublicKey;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Sertifikat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String alias;

    @Column
    private String namena;

    @Column
    private PublicKey publicKey;

    @Column
    private Date startDate;

    @Column
    private Date endDate;

    @Column
    private  String subjectEmail;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private Korisnik korisnik;

    @OneToOne(targetEntity = ZaKorisnika.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private ZaKorisnika zaKorisnika;

    @OneToOne(targetEntity = ZaUredjaj.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private ZaUredjaj zaUredjaj;

    @OneToOne(targetEntity = ZaMojaKucaAplikacija.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private ZaMojaKucaAplikacija zaMojaKucaAplikacija;

    public Sertifikat(String alias, String namena) {
        this.alias = alias;
        this.namena = namena;
    }
}
