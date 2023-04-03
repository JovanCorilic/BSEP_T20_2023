package Backend.AdminBackend.service;

import Backend.AdminBackend.model.Sertifikat;
import Backend.AdminBackend.repository.SertifikatRepository;
import Backend.AdminBackend.repository.ZaKorisnikaRepository;
import Backend.AdminBackend.repository.ZaMojaKucaAplikacijaRepository;
import Backend.AdminBackend.repository.ZaUredjajRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SertifikatService implements ServiceInterface<Sertifikat>{
    @Autowired
    private SertifikatRepository sertifikatRepository;

    @Autowired
    private ZaKorisnikaRepository zaKorisnikaRepository;

    @Autowired
    private ZaMojaKucaAplikacijaRepository zaMojaKucaAplikacijaRepository;

    @Autowired
    private ZaUredjajRepository zaUredjajRepository;

    @Override
    public List<Sertifikat> findAll() {
        return sertifikatRepository.findAll();
    }

    @Override
    public Sertifikat findOne(Integer id) {
        return null;
    }

    public Sertifikat findAlias(String alias){
        return sertifikatRepository.findByAlias(alias);
    }

    @Override
    public Sertifikat create(Sertifikat entity) {
        return null;
    }

    @Override
    public Sertifikat update(Sertifikat entity, Integer id) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        sertifikatRepository.delete(Objects.requireNonNull(sertifikatRepository.findById(id).orElse(null)));
    }
}
