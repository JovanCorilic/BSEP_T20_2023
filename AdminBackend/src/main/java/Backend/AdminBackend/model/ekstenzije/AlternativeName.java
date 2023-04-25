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
public class AlternativeName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String generalNameType;

    @Column
    private String generalNameContent;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn
    private SubjectAlternativeNameEkstenzije subjectAlternativeNameEkstenzije;

    public AlternativeName(String generalNameType, String generalNameContent) {
        this.generalNameType = generalNameType;
        this.generalNameContent = generalNameContent;
    }
}
