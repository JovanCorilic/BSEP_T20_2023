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
public class BasicConstraintsEkstenzije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean daLiKoristi;

    @Column
    private boolean isCritical;

    @Column
    private boolean isCA;

    @Column
    private int maxPathLen;

    public BasicConstraintsEkstenzije(boolean daLiKoristi, boolean isCritical, boolean isCA, int maxPathLen) {
        this.daLiKoristi = daLiKoristi;
        this.isCritical = isCritical;
        this.isCA = isCA;
        this.maxPathLen = maxPathLen;
    }
}
