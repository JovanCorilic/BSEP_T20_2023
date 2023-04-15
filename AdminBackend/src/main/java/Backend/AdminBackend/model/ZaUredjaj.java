package Backend.AdminBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ZaUredjaj {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String naziv;

    @Column
    private String svrha;

    @Column
    private String serijskiBroj;

    @OneToOne(mappedBy = "zaUredjaj", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn
    private ZahtevZaSertifikat zahtevZaSertifikat;

    public ZaUredjaj(Integer id, String naziv, String svrha, String serijskiBroj) {
        this.id = id;
        this.naziv = naziv;
        this.svrha = svrha;
        this.serijskiBroj = serijskiBroj;
    }

    public void Update(ZaUredjaj zaUredjaj){
        naziv = zaUredjaj.getNaziv();
        svrha = zaUredjaj.getSvrha();
        serijskiBroj = zaUredjaj.getSerijskiBroj();
    }
}
