package Backend.AdminBackend.repository;

import Backend.AdminBackend.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken,Integer> {
    VerificationToken findByToken(String url);
    VerificationToken findByZahtevZaSertifikatId(Integer id);
}
