package Backend.AdminBackend.model.ekstenzije;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BasicConstraintsEkstenzije {
    private boolean daLiKoristi;
    private boolean isCritical;
    private boolean isCA;
    private int maxPathLen;

}
