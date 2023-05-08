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

    public boolean proveraPodataka(){
        if (startDate == null || endDate == null || namena.isEmpty() || emailPotvrda.isEmpty() ||
                organizacionaJedinica.isEmpty() || nazivOrganizacije.isEmpty() || skraceniNazivZemlje.isEmpty())
            return true;
        if (ekstenzije != null)
            if (ekstenzije.proveraPodataka())
                return true;
        if (zaKorisnika != null)
            if (zaKorisnika.proveraPodataka())
                return true;
        if (zaMojaKucaAplikacija !=null)
            if (zaMojaKucaAplikacija.proveraPodataka())
                return true;
        if (zaUredjaj!=null)
            if (zaUredjaj.proveraPodataka())
                return true;
        return false;
    }

}
