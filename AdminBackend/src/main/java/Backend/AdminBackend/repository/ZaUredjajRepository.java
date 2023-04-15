package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.ZaUredjaj;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZaUredjajRepository extends JpaRepository<ZaUredjaj,Integer> {
    Optional<ZaUredjaj> findBySerijskiBroj(String serijskiBroj);
}
