package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.ZaUredjaj;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZaUredjajRepository extends JpaRepository<ZaUredjaj,Integer> {
    ZaUredjaj findBySerijskiBroj(String serijskiBroj);
}
