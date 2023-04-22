package Backend.AdminBackend.service;

import Backend.AdminBackend.dto.MusterijaDTO;
import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.model.Uloga;
import Backend.AdminBackend.model.VerificationToken;
import Backend.AdminBackend.ostalo.VerifikacioniTokenSlanjeEmail;
import Backend.AdminBackend.repository.KorisnikRepository;
import Backend.AdminBackend.repository.UlogaRepository;
import Backend.AdminBackend.repository.VerificationTokenRepository;
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Korisnik user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        } else {
            if (!user.isPotvrdjen())
                throw new UsernameNotFoundException(String.format("Korisnik nije odobren od strane admina '%s'.", email));
            return user;
        }
    }

    public List<Korisnik> SveMusterije(){
        return userRepository.findAllByUlogeContainingAndPotvrdjenIsTrue(ulogaRepository.findById(3).orElse(null));
    }

    public void potvrdiMusteriju(String email){
        Korisnik korisnik = userRepository.findByEmail(email);
        korisnik.setOdobrenOdAdmina(true);
        userRepository.save(korisnik);
    }

    public void register(MusterijaDTO musterijaDTO){
        List<Uloga>listaUloga = new ArrayList<>();
        listaUloga.add(ulogaRepository.findById(3).orElse(null));
        Korisnik korisnik = new Korisnik(musterijaDTO.getIme(), musterijaDTO.getPrezime(), musterijaDTO.getEmail(), musterijaDTO.getLozinka(), false,false,listaUloga);
        pravljenjePotvrde(userRepository.save(korisnik));
    }

    public void pravljenjeAdminNaloga(MusterijaDTO musterijaDTO){
        List<Uloga>listaUloga = new ArrayList<>();
        listaUloga.add(ulogaRepository.findById(1).orElse(null));
        Korisnik korisnik = new Korisnik(musterijaDTO.getIme(), musterijaDTO.getPrezime(), musterijaDTO.getEmail(), musterijaDTO.getLozinka(), false,true,listaUloga);
        pravljenjePotvrde(userRepository.save(korisnik));
    }

    @Async
    public void pravljenjePotvrde(Korisnik korisnik){
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

        VerifikacioniTokenSlanjeEmail thread = new VerifikacioniTokenSlanjeEmail(token,"/potvrdaZahteva",komeSalje,javaMailSender);
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
}
