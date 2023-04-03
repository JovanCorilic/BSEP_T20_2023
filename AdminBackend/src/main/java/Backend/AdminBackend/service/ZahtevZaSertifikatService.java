package Backend.AdminBackend.service;

import Backend.AdminBackend.model.*;
import Backend.AdminBackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

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
        zahtevZaSertifikat.setPrihvacen(true);
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
        Sertifikat temp = sertifikatRepository.save(sertifikat);
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
        potvrdaZahteva(entity);
        entity.setId(null);
        //uradi ovde proveru za duplikate
        /*ZaKorisnika zaKorisnika = zaKorisnikaRepository.findByEmail(entity.getZaKorisnika().getEmail());
        if (zaKorisnika==null)
            entity.getZaKorisnika().setId(null);
        else {
            entity.setZaKorisnika(zaKorisnika);
        }

        ZaUredjaj zaUredjaj = zaUredjajRepository.findBySerijskiBroj(entity.getZaUredjaj().getSerijskiBroj());
        if (zaUredjaj==null)
            entity.getZaUredjaj().setId(null);
        else{
            entity.setZaUredjaj(zaUredjaj);
        }

        ZaMojaKucaAplikacija zaMojaKucaAplikacija =
                zaMojaKucaAplikacijaRepository.findBySerijskiBroj(entity.getZaMojaKucaAplikacija().getSerijskiBroj());
        if (zaMojaKucaAplikacija==null)
            entity.getZaMojaKucaAplikacija().setId(null);
        else{
            entity.setZaMojaKucaAplikacija(zaMojaKucaAplikacija);
        }*/
        entity.getZaKorisnika().setId(null);
        entity.getZaUredjaj().setId(null);
        entity.getZaMojaKucaAplikacija().setId(null);

        return zahtevZaSertifikatRepository.save(entity);
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
        simpleMailMessage.setText(poruka+ "\r\n"+"http://localhost:4200/auth"+confirmURL);
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
        zahtevZaSertifikatRepository.delete(zahtevZaSertifikat);
    }
}
