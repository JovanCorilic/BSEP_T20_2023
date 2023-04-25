package Backend.AdminBackend.model;

import Backend.AdminBackend.model.ekstenzije.*;
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
public class Ekstenzije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(targetEntity = AuthorityKeyIdentifierEkstenzije.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private AuthorityKeyIdentifierEkstenzije authorityKeyIdentifierEkstenzije;

    @OneToOne(targetEntity = BasicConstraintsEkstenzije.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private BasicConstraintsEkstenzije basicConstraintsEkstenzije;

    @OneToOne(targetEntity = ExtendedKeyUsageEkstenzije.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private ExtendedKeyUsageEkstenzije extendedKeyUsageEkstenzije;

    @OneToOne(targetEntity = KeyUsageEkstenzije.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private KeyUsageEkstenzije keyUsageEkstenzije;

    @OneToOne(targetEntity = SubjectAlternativeNameEkstenzije.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private SubjectAlternativeNameEkstenzije subjectAlternativeNameEkstenzije;

    @OneToOne(targetEntity = SubjectKeyIdentifierEkstenzije.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private SubjectKeyIdentifierEkstenzije subjectKeyIdentifierEkstenzije;

    @OneToOne(mappedBy = "ekstenzije", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn
    private ZahtevZaSertifikat zahtevZaSertifikat;

    @OneToOne(mappedBy = "ekstenzije", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn
    private Sertifikat sertifikat;

    public Ekstenzije(AuthorityKeyIdentifierEkstenzije authorityKeyIdentifierEkstenzije, BasicConstraintsEkstenzije basicConstraintsEkstenzije, ExtendedKeyUsageEkstenzije extendedKeyUsageEkstenzije, KeyUsageEkstenzije keyUsageEkstenzije, SubjectAlternativeNameEkstenzije subjectAlternativeNameEkstenzije, SubjectKeyIdentifierEkstenzije subjectKeyIdentifierEkstenzije) {
        this.authorityKeyIdentifierEkstenzije = authorityKeyIdentifierEkstenzije;
        this.basicConstraintsEkstenzije = basicConstraintsEkstenzije;
        this.extendedKeyUsageEkstenzije = extendedKeyUsageEkstenzije;
        this.keyUsageEkstenzije = keyUsageEkstenzije;
        this.subjectAlternativeNameEkstenzije = subjectAlternativeNameEkstenzije;
        this.subjectKeyIdentifierEkstenzije = subjectKeyIdentifierEkstenzije;
    }
}
