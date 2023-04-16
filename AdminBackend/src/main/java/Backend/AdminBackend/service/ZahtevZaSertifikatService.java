package Backend.AdminBackend.service;

import Backend.AdminBackend.model.*;
import Backend.AdminBackend.repository.*;
import Backend.AdminBackend.security.certificate.PravljenjeSertifikata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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
        zahtevZaSertifikatRepository.save(zahtevZaSertifikat);
        Sertifikat sertifikat = new Sertifikat();
        switch (zahtevZaSertifikat.getNamena()) {
            case "Korisnik":
                sertifikat.setAlias(zahtevZaSertifikat.getZaKorisnika().getEmail() + zahtevZaSertifikat.getZaKorisnika().getId());
                sertifikat.setNamena(zahtevZaSertifikat.getNamena());
                sertifikat.setZaKorisnika(zahtevZaSertifikat.getZaKorisnika());
                break;
            case "Moja kuca aplikacija":
                sertifikat.setAlias(zahtevZaSertifikat.getZaMojaKucaAplikacija().getSerijskiBroj() + zahtevZaSertifikat.getZaMojaKucaAplikacija().getId());
                sertifikat.setNamena(zahtevZaSertifikat.getNamena());
                sertifikat.setZaMojaKucaAplikacija(zahtevZaSertifikat.getZaMojaKucaAplikacija());
                break;
            case "Uredjaj":
                sertifikat.setAlias(zahtevZaSertifikat.getZaUredjaj().getSerijskiBroj() + zahtevZaSertifikat.getZaUredjaj().getId());
                sertifikat.setNamena(zahtevZaSertifikat.getNamena());
                sertifikat.setZaUredjaj(sertifikat.getZaUredjaj());
                break;
            case "Admin aplikacija":
                //ponavljanje kod admina
                sertifikat.setAlias("admin");
                sertifikat.setNamena(zahtevZaSertifikat.getNamena());
                break;
        }
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Korisnik korisnik = (Korisnik) auth.getPrincipal();
        sertifikat.setKorisnik(korisnik);
        sertifikat.setStartDate(zahtevZaSertifikat.getStartDate());
        sertifikat.setEndDate(zahtevZaSertifikat.getEndDate());
        sertifikat.setSubjectEmail(zahtevZaSertifikat.getEmailPotvrda());
        Sertifikat temp = sertifikatRepository.save(sertifikat);
        temp.setPublicKey(PravljenjeSertifikata.pravljenje(temp));
        sertifikatRepository.save(temp);
    }

    @Override
    public List<ZahtevZaSertifikat> findAll() {
        return zahtevZaSertifikatRepository.findAll();
    }

    @Override
    public ZahtevZaSertifikat findOne(Integer id) {
        return zahtevZaSertifikatRepository.findById(id).orElse(null);
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

        zahtevZaSertifikatRepository.save(entity);
        potvrdaZahteva(entity);
        return null;
    }

    @Async
    public void potvrdaZahteva(ZahtevZaSertifikat zahtevSertifikat){
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
        String confirmURL = "/potvrdaZahteva/"+token;

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(komeSalje);
        simpleMailMessage.setSubject(naslov);
        simpleMailMessage.setText(poruka+ "\r\n"+"http://localhost:4200"+confirmURL);
        javaMailSender.send(simpleMailMessage);
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
        ZaMojaKucaAplikacija zaMojaKucaAplikacija = zaMojaKucaAplikacijaRepository.findById(zahtevZaSertifikat.getZaMojaKucaAplikacija().getId()).orElse(null);
        assert zaMojaKucaAplikacija != null;
        zaMojaKucaAplikacijaRepository.delete(zaMojaKucaAplikacija);
        zahtevZaSertifikatRepository.delete(zahtevZaSertifikat);
    }
}
