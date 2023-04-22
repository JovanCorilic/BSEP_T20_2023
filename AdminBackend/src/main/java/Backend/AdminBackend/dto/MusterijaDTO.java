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
}
