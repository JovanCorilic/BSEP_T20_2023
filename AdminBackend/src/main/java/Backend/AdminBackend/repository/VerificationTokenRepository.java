package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.model.VerificationToken;
import Backend.AdminBackend.model.ZahtevZaSertifikat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Integer> {
    VerificationToken findByToken(String url);
    VerificationToken findByZahtevZaSertifikatId(Integer id);

    @Transactional
    @Modifying
    Integer deleteByKorisnik(Korisnik korisnik);

    @Transactional
    @Modifying
    Integer deleteByZahtevZaSertifikat(ZahtevZaSertifikat zahtevZaSertifikat);
}
