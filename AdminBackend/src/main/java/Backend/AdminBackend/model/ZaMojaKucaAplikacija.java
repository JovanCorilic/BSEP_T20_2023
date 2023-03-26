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
public class ZaMojaKucaAplikacija {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String serijskiBroj;

    @OneToOne(mappedBy = "zaMojaKucaAplikacija", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn
    private ZahtevZaSertifikat zahtevZaSertifikat;

    public ZaMojaKucaAplikacija(Integer id, String serijskiBroj) {
        this.id = id;
        this.serijskiBroj = serijskiBroj;
    }
}
