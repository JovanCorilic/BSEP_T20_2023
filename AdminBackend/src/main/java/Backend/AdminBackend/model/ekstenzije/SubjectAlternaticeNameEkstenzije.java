package Backend.AdminBackend.model.ekstenzije;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubjectAlternaticeNameEkstenzije {
    private boolean daLiKoristi;
    private boolean isCritical;
    private List<AlternaticeName>listOfAlternativeNames;

}
