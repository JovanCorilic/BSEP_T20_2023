package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.ZaKorisnika;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZaKorisnikaRepository extends JpaRepository<ZaKorisnika,Integer> {
    Optional<ZaKorisnika> findByEmail(String email);
}
