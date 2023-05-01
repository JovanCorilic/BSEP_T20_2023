package Backend.AdminBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZahtevZaSertifikatDTO {
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String namena;
    private String emailPotvrda;

    private String organizacionaJedinica;
    private String nazivOrganizacije;
    private String skraceniNazivZemlje;

    private Boolean potvrdjenZahtev;
    private Boolean prihvacen;
    private ZaKorisnikaDTO zaKorisnika;
    private ZaUredjajDTO zaUredjaj;
    private ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacija;
    private KorisnikDTO musterija;
    private KorisnikDTO admin;
    private EkstenzijeDTO ekstenzije;

}
