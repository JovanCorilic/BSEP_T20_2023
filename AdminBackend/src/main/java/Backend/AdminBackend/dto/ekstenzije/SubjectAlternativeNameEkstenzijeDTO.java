package Backend.AdminBackend.dto.ekstenzije;

import Backend.AdminBackend.model.ekstenzije.AlternativeName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectAlternativeNameEkstenzijeDTO {

    private boolean daLiKoristi;
    private boolean daLiJeKriticno;
    private List<AlternativeNameDTO> alternativeNames =new ArrayList<>();

    public boolean proveraPodataka(){
        if (!alternativeNames.isEmpty()){
            for (AlternativeNameDTO name : alternativeNames){
                if (name.proveraPodataka())
                    return true;
            }
        }
        return false;
    }

}
