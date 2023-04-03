package Backend.AdminBackend.service;

import Backend.AdminBackend.model.PovlacenjeSertifikata;
import Backend.AdminBackend.model.Sertifikat;
import Backend.AdminBackend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SertifikatService implements ServiceInterface<Sertifikat>{
    @Autowired
    private SertifikatRepository sertifikatRepository;

    @Autowired
    private PovlacenjeSertifikataRepository povlacenjeSertifikataRepository;

    public void povlacenjeSertifikata(String alias,String razlog){
        PovlacenjeSertifikata povlacenjeSertifikata = new PovlacenjeSertifikata();
        povlacenjeSertifikata.setRazlog(razlog);
        povlacenjeSertifikata.setAlias(alias);
        povlacenjeSertifikataRepository.save(povlacenjeSertifikata);
    }

    public PovlacenjeSertifikata dajPovuceniSertifikat(String alias){
        return povlacenjeSertifikataRepository.findByAlias(alias);
    }

    public List<PovlacenjeSertifikata> SviPovuceniSertifikati(){
        return povlacenjeSertifikataRepository.findAll();
    }

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
        //sertifikatRepository.delete(Objects.requireNonNull(sertifikatRepository.findById(id).orElse(null)));
    }
}