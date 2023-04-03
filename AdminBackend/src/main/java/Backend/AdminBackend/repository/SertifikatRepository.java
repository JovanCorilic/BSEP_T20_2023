package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.Sertifikat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SertifikatRepository extends JpaRepository<Sertifikat,Integer> {
    Sertifikat findByAlias(String alias);
}
