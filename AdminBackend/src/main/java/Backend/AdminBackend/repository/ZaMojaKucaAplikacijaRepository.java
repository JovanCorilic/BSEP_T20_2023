package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.ZaMojaKucaAplikacija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZaMojaKucaAplikacijaRepository extends JpaRepository<ZaMojaKucaAplikacija,Integer> {
    ZaMojaKucaAplikacija findBySerijskiBroj(String serijskiBroj);
}
