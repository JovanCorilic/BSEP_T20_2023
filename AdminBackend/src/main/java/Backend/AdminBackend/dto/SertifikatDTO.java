package Backend.AdminBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SertifikatDTO {
    private String alias;
    private String namena;
    private Date startDate;
    private Date endDate;
    private  String subjectEmail;

    private String organizacionaJedinica;
    private String nazivOrganizacije;
    private String skraceniNazivZemlje;

    private ZaKorisnikaDTO zaKorisnika;
    private ZaUredjajDTO zaUredjaj;
    private ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacija;
    private KorisnikDTO musterija;
    private KorisnikDTO admin;
    private EkstenzijeDTO ekstenzije;
}
