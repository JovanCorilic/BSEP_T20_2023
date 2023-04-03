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
public class ZahtevZaSertifikatShortDTO {
    private Integer id;
    private Date startDate;
    private Date endDate;
    private String namena;

    private String emailPotvrda;
    private Boolean potvrdjenZahtev;
    private Boolean prihvacen;
}
