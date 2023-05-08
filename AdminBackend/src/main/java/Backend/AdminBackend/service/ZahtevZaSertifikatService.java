package Backend.AdminBackend.service;

import Backend.AdminBackend.model.*;
import Backend.AdminBackend.model.ekstenzije.AlternativeName;
import Backend.AdminBackend.model.ekstenzije.SubjectAlternativeNameEkstenzije;
import Backend.AdminBackend.ostalo.VerifikacioniTokenSlanjeEmail;
import Backend.AdminBackend.repository.*;
import Backend.AdminBackend.repository.ekstenzije.*;
import Backend.AdminBackend.security.certificate.PravljenjeSertifikata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

@Service
public class ZahtevZaSertifikatService implements ServiceInterface<ZahtevZaSertifikat> {
    @Autowired
    private ZahtevZaSertifikatRepository zahtevZaSertifikatRepository;

    @Autowired
    private ZaKorisnikaRepository zaKorisnikaRepository;

    @Autowired
    private ZaMojaKucaAplikacijaRepository zaMojaKucaAplikacijaRepository;

    @Autowired
    private ZaUredjajRepository zaUredjajRepository;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    @Autowired
    private SertifikatRepository sertifikatRepository;
    @Autowired
    private KorisnikRepository korisnikRepository;

    //Ekstenzije
    @Autowired
    private AlternativeNameRepository alternativeNameRepository;
    @Autowired
    private AuthorityKeyIdentifierEkstenzijeRepository authorityKeyIdentifierEkstenzijeRepository;
    @Autowired
    private BasicConstraintsEkstenzijeRepository basicConstraintsEkstenzijeRepository;
    @Autowired
    private ExtendedKeyUsageEkstenzijeRepository extendedKeyUsageEkstenzijeRepository;
    @Autowired
    private KeyUsageEkstenzijeRepository keyUsageEkstenzijeRepository;
    @Autowired
    private SubjectAlternativeNameEkstenzijeRepository subjectAlternativeNameEkstenzijeRepository;
    @Autowired
    private SubjectKeyIdentifierEkstenzijeRepository subjectKeyIdentifierEkstenzijeRepository;
    @Autowired
    private EkstenzijeRepository ekstenzijeRepository;

    public void napraviSertifikatOdZahteva(ZahtevZaSertifikat zahtevZaSertifikat){
        if (!zahtevZaSertifikat.getPotvrdjenZahtev())
            return;
        zahtevZaSertifikat = zahtevZaSertifikatRepository.findById(zahtevZaSertifikat.getId()).orElse(null);
        zahtevZaSertifikat.setPrihvacen(true);
        /*
        switch (zahtevZaSertifikat.getNamena()) {
            case "Uredjaj":
                zaUredjajRepository.save(zahtevZaSertifikat.getZaUredjaj());
                break;
            case "Moja kuca aplikacija":
                zaMojaKucaAplikacijaRepository.save(zahtevZaSertifikat.getZaMojaKucaAplikacija());
                break;
            case "Korisnik":
                zaKorisnikaRepository.save(zahtevZaSertifikat.getZaKorisnika());
                break;
        }*/

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = (Korisnik) auth.getPrincipal();
        zahtevZaSertifikat.setAdmin(korisnikRepository.findByEmail(korisnik.getEmail()));

        zahtevZaSertifikatRepository.save(zahtevZaSertifikat);
        Sertifikat sertifikat = new Sertifikat();
        switch (zahtevZaSertifikat.getNamena()) {
            case "Korisnik":
                sertifikat.setAlias(zahtevZaSertifikat.getZaKorisnika().getEmail() + zahtevZaSertifikat.getZaKorisnika().getId());
                sertifikat.setNamena(zahtevZaSertifikat.getNamena());
                sertifikat.setZaKorisnika(zahtevZaSertifikat.getZaKorisnika());
                break;
            case "Moja kuca aplikacija":
                sertifikat.setAlias("mojaKucaAplikacija"+zahtevZaSertifikat.getZaMojaKucaAplikacija().getSerijskiBroj()
                        +"("+ zahtevZaSertifikat.getZaMojaKucaAplikacija().getId() + ")");
                sertifikat.setNamena(zahtevZaSertifikat.getNamena());
                sertifikat.setZaMojaKucaAplikacija(zahtevZaSertifikat.getZaMojaKucaAplikacija());
                break;
            case "Uredjaj":
                sertifikat.setAlias("uredjaj"+zahtevZaSertifikat.getZaUredjaj().getSerijskiBroj() +"("+ zahtevZaSertifikat.getZaUredjaj().getId()+ ")");
                sertifikat.setNamena(zahtevZaSertifikat.getNamena());
                sertifikat.setZaUredjaj(zahtevZaSertifikat.getZaUredjaj());
                break;
            case "Za mene":
                //ponavljanje kod admina
                sertifikat.setAlias("zaMene"+zahtevZaSertifikat.getMusterija().getEmail());
                sertifikat.setNamena(zahtevZaSertifikat.getNamena());
                break;
        }

        sertifikat.setAdmin(zahtevZaSertifikat.getAdmin());
        sertifikat.setMusterija(zahtevZaSertifikat.getMusterija());

        sertifikat.setStartDate(zahtevZaSertifikat.getStartDate());
        sertifikat.setEndDate(zahtevZaSertifikat.getEndDate());
        sertifikat.setSubjectEmail(zahtevZaSertifikat.getEmailPotvrda());

        sertifikat.setOrganizacionaJedinica(zahtevZaSertifikat.getOrganizacionaJedinica());
        sertifikat.setNazivOrganizacije(zahtevZaSertifikat.getNazivOrganizacije());
        sertifikat.setSkraceniNazivZemlje(zahtevZaSertifikat.getSkraceniNazivZemlje());

        sertifikat.setEkstenzije(zahtevZaSertifikat.getEkstenzije());
        Sertifikat temp = sertifikatRepository.save(sertifikat);
        temp.setPublicKey(PravljenjeSertifikata.pravljenje(temp));
        sertifikatRepository.save(temp);
    }

    @Override
    public List<ZahtevZaSertifikat> findAll() {
        return zahtevZaSertifikatRepository.findAll();
    }

    public List<ZahtevZaSertifikat>findAllZaMusteriju(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = (Korisnik) auth.getPrincipal();
        return zahtevZaSertifikatRepository.findAllByMusterijaIs(korisnikRepository.findByEmail(korisnik.getEmail()));
    }

    @Override
    public ZahtevZaSertifikat findOne(Integer id) {
        return zahtevZaSertifikatRepository.findById(id).orElse(null);
    }

    public ZahtevZaSertifikat findOneMusterija(Integer id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = (Korisnik) auth.getPrincipal();
        korisnik = korisnikRepository.findByEmail(korisnik.getEmail());
        return zahtevZaSertifikatRepository.findByIdAndMusterija(id,korisnik);
    }

    @Override
    public ZahtevZaSertifikat create(ZahtevZaSertifikat entity){

        entity.setId(null);
        //uradi ovde proveru za duplikate
        if (entity.getZaKorisnika()!=null) {
            ZaKorisnika zaKorisnika = zaKorisnikaRepository.findByEmail(entity.getZaKorisnika().getEmail()).orElse(null);
            if (zaKorisnika == null) {
                entity.setZaKorisnika(zaKorisnikaRepository.save(entity.getZaKorisnika()));
            } else {
                entity.setZaKorisnika(zaKorisnika);
            }
        }

        if (entity.getZaUredjaj()!=null) {
            ZaUredjaj zaUredjaj = zaUredjajRepository.findBySerijskiBroj(entity.getZaUredjaj().getSerijskiBroj()).orElse(null);
            if (zaUredjaj == null) {
                entity.setZaUredjaj(zaUredjajRepository.save(entity.getZaUredjaj()));
            } else {
                entity.setZaUredjaj(zaUredjaj);
            }
        }

        if (entity.getZaMojaKucaAplikacija()!=null) {
            ZaMojaKucaAplikacija zaMojaKucaAplikacija =
                    zaMojaKucaAplikacijaRepository.findBySerijskiBroj(entity.getZaMojaKucaAplikacija().getSerijskiBroj()).orElse(null);
            if (zaMojaKucaAplikacija == null) {
                entity.setZaMojaKucaAplikacija(zaMojaKucaAplikacijaRepository.save(entity.getZaMojaKucaAplikacija()));
            } else {
                entity.setZaMojaKucaAplikacija(zaMojaKucaAplikacija);
            }
        }

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = (Korisnik) auth.getPrincipal();
        entity.setMusterija(korisnikRepository.findByEmail(korisnik.getEmail()));

        Ekstenzije ekstenzije = ekstenzijeRepository.save(dodavanjeEkstenzijaUBazu(entity.getEkstenzije()));
        entity.setEkstenzije(ekstenzije);

        zahtevZaSertifikatRepository.save(entity);
        pravljenjePotvrdeZahteva(entity);
        return null;
    }

    public Ekstenzije dodavanjeEkstenzijaUBazu(Ekstenzije ekstenzije){
        if (ekstenzije.getAuthorityKeyIdentifierEkstenzije().isDaLiKoristi()){
            authorityKeyIdentifierEkstenzijeRepository.save(ekstenzije.getAuthorityKeyIdentifierEkstenzije());
        }else
            ekstenzije.setAuthorityKeyIdentifierEkstenzije(null);

        if (ekstenzije.getBasicConstraintsEkstenzije().isDaLiKoristi()){
            basicConstraintsEkstenzijeRepository.save(ekstenzije.getBasicConstraintsEkstenzije());
        }else
            ekstenzije.setBasicConstraintsEkstenzije(null);

        if (ekstenzije.getExtendedKeyUsageEkstenzije().isDaLiKoristi()){
            extendedKeyUsageEkstenzijeRepository.save(ekstenzije.getExtendedKeyUsageEkstenzije());
        }else
            ekstenzije.setExtendedKeyUsageEkstenzije(null);

        if (ekstenzije.getKeyUsageEkstenzije().isDaLiKoristi()){
            keyUsageEkstenzijeRepository.save(ekstenzije.getKeyUsageEkstenzije());
        }else
            ekstenzije.setKeyUsageEkstenzije(null);

        if (ekstenzije.getSubjectAlternativeNameEkstenzije().isDaLiKoristi()){
            List<AlternativeName>lista = alternativeNameRepository.saveAll(ekstenzije.getSubjectAlternativeNameEkstenzije().getAlternativeNames());
            Set<AlternativeName> listaPrava = new HashSet<>(lista);
            ekstenzije.getSubjectAlternativeNameEkstenzije().setAlternativeNames(listaPrava);
            SubjectAlternativeNameEkstenzije subjectAlternativeNameEkstenzije = subjectAlternativeNameEkstenzijeRepository.save(ekstenzije.getSubjectAlternativeNameEkstenzije());
            for (AlternativeName name : lista){
                name.setSubjectAlternativeNameEkstenzije(subjectAlternativeNameEkstenzije);
            }
            alternativeNameRepository.saveAll(lista);
        }else
            ekstenzije.setSubjectAlternativeNameEkstenzije(null);

        if (ekstenzije.getSubjectKeyIdentifierEkstenzije().isDaLiKoristi()){
            subjectKeyIdentifierEkstenzijeRepository.save(ekstenzije.getSubjectKeyIdentifierEkstenzije());
        }else
            ekstenzije.setSubjectKeyIdentifierEkstenzije(null);

        return ekstenzije;
    }

    @Async
    public void pravljenjePotvrdeZahteva(ZahtevZaSertifikat zahtevSertifikat){
        String komeSalje = zahtevSertifikat.getEmailPotvrda();
        String naslov = "Potvrda zahteva";

        String poruka = "Aktivacioni link: ";
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setZahtevZaSertifikat(zahtevSertifikat);
        Date date = verificationToken.calculateExpiryDate(1440);
        verificationToken.setExpiryDate(date);
        verificationTokenRepository.save(verificationToken);

        VerifikacioniTokenSlanjeEmail thread = new VerifikacioniTokenSlanjeEmail(token,"/potvrdaZahteva",komeSalje,javaMailSender);
        thread.start();
    }

    public void potvrdaZahteva(String token) throws Exception {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        ZahtevZaSertifikat zahtevSertifikat = verificationToken.getZahtevZaSertifikat();
        assert zahtevSertifikat != null;
        Date date = new Date();
        if (!verificationToken.getExpiryDate().before(date))
            zahtevSertifikat.setPotvrdjenZahtev(true);
        else
            throw new Exception("Istekao");
        zahtevZaSertifikatRepository.save(zahtevSertifikat);
    }

    @Override
    public ZahtevZaSertifikat update(ZahtevZaSertifikat entity, Integer id){
        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatRepository.findById(id).orElse(null);
        assert zahtevZaSertifikat != null;
        if (zahtevZaSertifikat.getPrihvacen())
            return null;
        zahtevZaSertifikat.Update(entity);
        return zahtevZaSertifikatRepository.save(zahtevZaSertifikat);
    }

    @Override
    public void delete(Integer id){
        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatRepository.findById(id).orElse(null);
        assert zahtevZaSertifikat != null;
        zahtevZaSertifikat.setPrihvacen(true);
        zahtevZaSertifikatRepository.save(zahtevZaSertifikat);
    }
}
