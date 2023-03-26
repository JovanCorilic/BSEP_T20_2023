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

}
