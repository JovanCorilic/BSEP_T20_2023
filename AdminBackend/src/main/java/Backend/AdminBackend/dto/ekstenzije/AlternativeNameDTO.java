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
public class AlternativeNameDTO {
    private String generalNameType;
    private String generalNameContent;

    public boolean proveraPodataka(){
        return generalNameType.isEmpty() || generalNameContent.isEmpty();
    }
}
