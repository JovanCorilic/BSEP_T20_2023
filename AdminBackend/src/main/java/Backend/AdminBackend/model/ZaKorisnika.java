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
public class ZaKorisnika {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String ime;

    @Column
    private String prezime;

    @OneToOne(mappedBy = "zaKorisnika", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn
    private ZahtevZaSertifikat zahtevZaSertifikat;

    public ZaKorisnika(Integer id, String email, String ime, String prezime) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
    }
}
