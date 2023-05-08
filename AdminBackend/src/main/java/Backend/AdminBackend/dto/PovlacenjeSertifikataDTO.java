package Backend.AdminBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PovlacenjeSertifikataDTO {
    private String razlog;
    private String alias;

    public boolean proveraPodataka(){
        return razlog.isEmpty() || alias.isEmpty();
    }
}
