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
public class KeyUsageEkstenzije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean daLiKoristi;

    @Column
    private boolean isCritical;

    @Column
    private boolean digitalSignature;

    @Column
    private boolean nonRepudiation;

    @Column
    private boolean keyEncipherment;

    @Column
    private boolean dataEncipherment;

    @Column
    private boolean keyAgreement;

    @Column
    private boolean keyCertSign;

    @Column
    private boolean cRLSign;

    @Column
    private boolean encipherOnly;

    @Column
    private boolean decipherOnly;

    public KeyUsageEkstenzije(boolean daLiKoristi, boolean isCritical, boolean digitalSignature, boolean nonRepudiation, boolean keyEncipherment, boolean dataEncipherment, boolean keyAgreement, boolean keyCertSign, boolean cRLSign, boolean encipherOnly, boolean decipherOnly) {
        this.daLiKoristi = daLiKoristi;
        this.isCritical = isCritical;
        this.digitalSignature = digitalSignature;
        this.nonRepudiation = nonRepudiation;
        this.keyEncipherment = keyEncipherment;
        this.dataEncipherment = dataEncipherment;
        this.keyAgreement = keyAgreement;
        this.keyCertSign = keyCertSign;
        this.cRLSign = cRLSign;
        this.encipherOnly = encipherOnly;
        this.decipherOnly = decipherOnly;
    }
}
