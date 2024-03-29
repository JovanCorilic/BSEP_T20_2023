package Backend.AdminBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusterijaDTO {
    private String ime;
    private String prezime;
    private String email;
    private String lozinka;

    public boolean proveraPodataka(){
        if (ime.isEmpty() || prezime.isEmpty() || email.isEmpty() || lozinka.isEmpty())
            return true;
        if (!lozinka.matches(".*\\d+.*") || !lozinka.matches(".*[^a-zA-Z0-9 ].*")){
            return true;
        }
        return false;
    }
}
