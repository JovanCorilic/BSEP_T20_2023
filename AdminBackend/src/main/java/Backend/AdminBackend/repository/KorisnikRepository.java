package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KorisnikRepository extends JpaRepository<Korisnik,Integer> {
    Korisnik findByEmail(String email);
}
