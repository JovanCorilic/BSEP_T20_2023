package Backend.AdminBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.security.PublicKey;

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
