package Backend.AdminBackend.model.ekstenzije;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AuthorityKeyIdentifierEkstenzije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean daLiKoristi;

    @Column
    private boolean isCritical;

    public AuthorityKeyIdentifierEkstenzije(boolean daLiKoristi, boolean isCritical) {
        this.daLiKoristi = daLiKoristi;
        this.isCritical = isCritical;
    }
}
