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

public class BasicConstraintsEkstenzijeDTO {


    private boolean daLiKoristi;
    private boolean daLiJeKriticno;

    private boolean daLiJeCA;

    private int maxPathLen;

    public boolean proveraPodataka(){
        return maxPathLen  <= 0;
    }

}
