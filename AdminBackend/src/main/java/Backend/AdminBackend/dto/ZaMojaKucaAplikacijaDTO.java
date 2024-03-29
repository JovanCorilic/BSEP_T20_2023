package Backend.AdminBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ZaMojaKucaAplikacijaDTO {
    private String serijskiBroj;

    public boolean proveraPodataka(){
        return serijskiBroj.isEmpty();
    }
}
