package Backend.AdminBackend.service;

import Backend.AdminBackend.dto.MusterijaDTO;
import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.model.Uloga;
import Backend.AdminBackend.model.VerificationToken;
import Backend.AdminBackend.ostalo.VerifikacioniTokenSlanjeEmail;
import Backend.AdminBackend.repository.KorisnikRepository;
import Backend.AdminBackend.repository.UlogaRepository;
import Backend.AdminBackend.repository.VerificationTokenRepository;
import Backend.AdminBackend.security.password.CustomPasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private KorisnikRepository userRepository;
    @Autowired
    private UlogaRepository ulogaRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private VerificationTokenRepository verificationTokenRepository;
    private final CustomPasswordEncoder customPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Korisnik user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else {
            if (!user.isOdobrenOdAdmina() || !user.isPotvrdjen())
                throw new UsernameNotFoundException(String.format("Korisnik nije odobren od strane admina '%s'.", email));
            return user;
        }
    }

    public List<Korisnik> SveMusterije(){
        return userRepository.findAllByUlogeContainingAndPotvrdjenIsTrueAndOdobrenOdAdminaIsFalse(ulogaRepository.findById(3).orElse(null));
    }

    public void potvrdiMusteriju(String email){
        Korisnik korisnik = userRepository.findByEmail(email);
        korisnik.setOdobrenOdAdmina(true);
        userRepository.save(korisnik);
    }

    public boolean daLiSeVecKoristiEmail(String email){
        return userRepository.existsKorisnikByEmail(email);
    }

    public void register(MusterijaDTO musterijaDTO){
        List<Uloga>listaUloga = new ArrayList<>();
        listaUloga.add(ulogaRepository.findById(3).orElse(null));
        Korisnik korisnik = new Korisnik(musterijaDTO.getIme(), musterijaDTO.getPrezime(), musterijaDTO.getEmail(), musterijaDTO.getLozinka(), false,false,listaUloga);
        korisnik.setId(userRepository.findAll().size()+1);
        korisnik.setLozinka(customPasswordEncoder.encode(korisnik.getLozinka()));
        pravljenjePotvrde(userRepository.save(korisnik),"/verifikacijaRegistracija");
    }

    public void pravljenjeAdminNaloga(MusterijaDTO musterijaDTO){
        List<Uloga>listaUloga = new ArrayList<>();
        listaUloga.add(ulogaRepository.findById(1).orElse(null));
        Korisnik korisnik = new Korisnik(musterijaDTO.getIme(), musterijaDTO.getPrezime(), musterijaDTO.getEmail(), musterijaDTO.getLozinka(), false,true,listaUloga);
        korisnik.setId(userRepository.findAll().size()+1);
        korisnik.setLozinka(customPasswordEncoder.encode(korisnik.getLozinka()));
        pravljenjePotvrde(userRepository.save(korisnik),"/VerifikacijaAdminPravljenje");
    }

    @Async
    public void pravljenjePotvrde(Korisnik korisnik,String adresa){
        String komeSalje = korisnik.getEmail();
        String naslov = "Potvrda zahteva";

        String poruka = "Aktivacioni link: ";
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setKorisnik(korisnik);
        Date date = verificationToken.calculateExpiryDate(1440);
        verificationToken.setExpiryDate(date);
        verificationTokenRepository.save(verificationToken);

        VerifikacioniTokenSlanjeEmail thread = new VerifikacioniTokenSlanjeEmail(token,adresa,komeSalje,javaMailSender);
        thread.start();
    }

    public void potvrdaNaloga(String token) throws Exception {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        Korisnik korisnik = verificationToken.getKorisnik();
        assert korisnik != null;
        Date date = new Date();
        if (!verificationToken.getExpiryDate().before(date))
            korisnik.setPotvrdjen(true);
        else
            throw new Exception("Istekao");
        userRepository.save(korisnik);
    }

    public void delete(String email){
        Korisnik korisnik = userRepository.findByEmail(email);
        verificationTokenRepository.deleteByKorisnik(korisnik);
        userRepository.delete(korisnik);
    }

    public CustomUserDetailsService() {
        this.customPasswordEncoder = new CustomPasswordEncoder();
    }
}
