package Backend.AdminBackend.dto.ekstenzije;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KeyUsageEkstenzijeDTO {


    private boolean daLiKoristi;

    private boolean isCritical;

    private boolean digitalSignature;

    private boolean nonRepudiation;

    private boolean keyEncipherment;

    private boolean dataEncipherment;

    private boolean keyAgreement;

    private boolean keyCertSign;

    private boolean cRLSign;

    private boolean encipherOnly;

    private boolean decipherOnly;
}
