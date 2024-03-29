package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.ZaMojaKucaAplikacija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ZaMojaKucaAplikacijaRepository extends JpaRepository<ZaMojaKucaAplikacija,Integer> {
    Optional<ZaMojaKucaAplikacija> findBySerijskiBroj(String serijskiBroj);
}
