package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.model.Uloga;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface KorisnikRepository extends JpaRepository<Korisnik,Integer> {
    Korisnik findByEmail(String email);

    List<Korisnik> findAllByUlogeContainingAndPotvrdjenIsTrueAndOdobrenOdAdminaIsFalse(Uloga uloga);
}
