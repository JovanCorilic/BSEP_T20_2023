package Backend.AdminBackend.service;

import Backend.AdminBackend.model.VerificationToken;
import Backend.AdminBackend.model.ZahtevZaSertifikat;
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
