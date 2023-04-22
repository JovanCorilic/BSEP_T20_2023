package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.model.ZahtevZaSertifikat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ZahtevZaSertifikatRepository extends JpaRepository<ZahtevZaSertifikat,Integer> {
    List<ZahtevZaSertifikat> findAllByMusterijaIs(Korisnik korisnik);
}
