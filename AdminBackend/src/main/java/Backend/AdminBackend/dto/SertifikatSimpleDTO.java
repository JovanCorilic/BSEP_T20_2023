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
public class SertifikatSimpleDTO {
    private String alias;
    private String namena;
    private Date startDate;
    private Date endDate;
    private  String subjectEmail;
    private String adminEmail;
}
