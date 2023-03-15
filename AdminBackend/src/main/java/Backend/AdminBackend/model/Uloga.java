package Backend.AdminBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Uloga implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private UlogaEnum naziv;

    @ManyToMany(mappedBy = "uloge")
    private List<Korisnik> korisnikList;

    @Override
    public String getAuthority() {
        return naziv.getUlogaNaziv();
    }

    @Override
    public boolean equals(Object obj) {
        Uloga o = (Uloga) obj;
        return this.getId().equals(o.getId()) && this.getNaziv().getUlogaNaziv().equals(o.getNaziv().getUlogaNaziv());
    }
}
