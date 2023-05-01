package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.PovlacenjeSertifikata;
import Backend.AdminBackend.model.Sertifikat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PovlacenjeSertifikataRepository extends JpaRepository<PovlacenjeSertifikata,Integer> {
    PovlacenjeSertifikata findByAlias(String alias);

    boolean existsByAlias(String alias);
}
