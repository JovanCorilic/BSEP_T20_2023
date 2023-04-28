package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.model.Sertifikat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SertifikatRepository extends JpaRepository<Sertifikat,Integer> {
    Sertifikat findByAlias(String alias);

    List<Sertifikat> findAllByMusterija(Korisnik korisnik);
}
