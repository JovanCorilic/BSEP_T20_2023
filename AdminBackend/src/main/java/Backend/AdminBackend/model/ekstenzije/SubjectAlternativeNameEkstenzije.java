package Backend.AdminBackend.model.ekstenzije;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubjectAlternativeNameEkstenzije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean daLiKoristi;

    @Column
    private boolean isCritical;

    @OneToMany(mappedBy = "subjectAlternativeNameEkstenzije", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<AlternativeName> alternativeNames =new HashSet<>();

    public SubjectAlternativeNameEkstenzije(boolean daLiKoristi, boolean isCritical, Set<AlternativeName> alternativeNames) {
        this.daLiKoristi = daLiKoristi;
        this.isCritical = isCritical;
        this.alternativeNames = alternativeNames;
    }
}
