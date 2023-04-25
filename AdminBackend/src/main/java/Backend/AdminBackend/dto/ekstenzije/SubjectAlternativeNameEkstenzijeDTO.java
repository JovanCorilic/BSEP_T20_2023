package Backend.AdminBackend.dto.ekstenzije;

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
    private boolean isCritical;
    private List<AlternativeNameDTO> alternativeNames =new ArrayList<>();

}
