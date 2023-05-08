package Backend.AdminBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZaUredjajDTO {
    private String naziv;
    private String svrha;
    private String serijskiBroj;

    public boolean proveraPodataka(){
        return naziv.isEmpty() || svrha.isEmpty() || serijskiBroj.isEmpty();
    }

}
