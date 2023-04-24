package Backend.AdminBackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Korisnik implements UserDetails {

    @Id
    //@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "user_id")
    private Integer id;

    @Column(nullable=false)
    private String ime;

    @Column(nullable=false)
    private String prezime;

    @Column(nullable=false,unique = true)
    private String email;

    @Column(nullable=false)
    private String lozinka;

    @Column(nullable = false)
    private boolean potvrdjen;

    @Column(nullable = false)
    private boolean odobrenOdAdmina;

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Uloga> uloge;

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sertifikat> sertifikatsAdmin=new HashSet<>();

    @OneToMany(mappedBy = "musterija", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Sertifikat> sertifikatsMusterija=new HashSet<>();

    @OneToMany(mappedBy = "musterija", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ZahtevZaSertifikat> zahtevZaSertifikatsMusterija = new HashSet<>();

    @OneToMany(mappedBy = "admin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<ZahtevZaSertifikat> zahtevZaSertifikatsAdmin = new HashSet<>();

    public Korisnik(Integer id, String ime, String prezime, String email, String lozinka) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
    }

    public Korisnik(String ime, String prezime, String email, String lozinka, boolean potvrdjen, boolean odobrenOdAdmina, List<Uloga> uloge) {
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.lozinka = lozinka;
        this.potvrdjen = potvrdjen;
        this.odobrenOdAdmina = odobrenOdAdmina;
        this.uloge = uloge;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.uloge;
    }

    @Override
    public String getPassword() {
        return this.lozinka;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
