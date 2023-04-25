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

public class AuthorityKeyIdentifierEkstenzijeDTO {

    private boolean daLiKoristi;

    private boolean isCritical;
}
