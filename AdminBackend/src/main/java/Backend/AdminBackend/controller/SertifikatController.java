package Backend.AdminBackend.controller;

import Backend.AdminBackend.dto.*;
import Backend.AdminBackend.mapper.PovlacenjeSertifikataMapper;
import Backend.AdminBackend.mapper.SertifikatMapper;
import Backend.AdminBackend.mapper.ZahtevZaSertifikatMapper;
import Backend.AdminBackend.model.PovlacenjeSertifikata;
import Backend.AdminBackend.model.Sertifikat;
import Backend.AdminBackend.model.ZahtevZaSertifikat;
import Backend.AdminBackend.service.SertifikatService;
import Backend.AdminBackend.service.ZahtevZaSertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/sertifikat")
public class SertifikatController {

    @Autowired
    private ZahtevZaSertifikatService zahtevZaSertifikatService;

    @Autowired
    private SertifikatService sertifikatService;

    private final ZahtevZaSertifikatMapper zahtevZaSertifikatMapper;
    private final SertifikatMapper sertifikatMapper;
    private final PovlacenjeSertifikataMapper povlacenjeSertifikataMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dajPovuceniSertifikat/{alias}")
    public ResponseEntity<PovlacenjeSertifikataDTO> dajPovuceniSertifikat(@PathVariable String alias){
        PovlacenjeSertifikata povlacenjeSertifikata = sertifikatService.dajPovuceniSertifikat(alias);
        return new ResponseEntity<>(povlacenjeSertifikataMapper.toDto(povlacenjeSertifikata),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dajSvePovuceneSertifikate")
    public ResponseEntity<List<PovlacenjeSertifikataDTO>> sviPovuceniSertifikati(){
        List<PovlacenjeSertifikata>povlacenjeSertifikatas = sertifikatService.SviPovuceniSertifikati();
        List<PovlacenjeSertifikataDTO>lista = new ArrayList<>();
        for (PovlacenjeSertifikata povlacenjeSertifikata : povlacenjeSertifikatas){
            lista.add(povlacenjeSertifikataMapper.toDto(povlacenjeSertifikata));
        }
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/povuciSertifikat")
    public ResponseEntity<?> povuciSertifikat(@RequestBody PovlacenjeSertifikataDTO povlacenjeSertifikataDTO){
        sertifikatService.povlacenjeSertifikata(povlacenjeSertifikataDTO.getAlias(),povlacenjeSertifikataDTO.getRazlog());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dajSveSertifikate")
    public ResponseEntity<List<SertifikatSimpleDTO>>getAllSertifikate(){
        List<Sertifikat> sertifikats = sertifikatService.findAll();
        List<SertifikatSimpleDTO>lista = new ArrayList<>();
        for (Sertifikat sertifikat : sertifikats){
            lista.add(new SertifikatSimpleDTO(sertifikat.getAlias(),sertifikat.getNamena(),sertifikat.getStartDate(),sertifikat.getEndDate(),sertifikat.getSubjectEmail(),sertifikat.getKorisnik().getEmail()));
        }
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dajSertifikat/{alias}")
    public ResponseEntity<SertifikatDTO> getSertifikat(@PathVariable String alias){
        Sertifikat sertifikat = sertifikatService.findAlias(alias);
        SertifikatDTO sertifikatDTO = sertifikatMapper.toDto(sertifikat);
        return new ResponseEntity<>(sertifikatDTO,HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/napraviMini/{id}")
    public ResponseEntity<?> createSertifikatMini(@PathVariable Integer id){

        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatService.findOne(id);
        zahtevZaSertifikatService.napraviSertifikatOdZahteva(zahtevZaSertifikat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/napravi")
    public ResponseEntity<?> createSertifikat(@RequestBody ZahtevZaSertifikatDTO zahtevZaSertifikatDTO){
        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatMapper.toModel(zahtevZaSertifikatDTO);
        zahtevZaSertifikatService.napraviSertifikatOdZahteva(zahtevZaSertifikat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/createZahtevZaSertifikat", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createZahtevZaSertifikat(@RequestBody Object temp){
        ZahtevZaSertifikatDTO zahtevZaSertifikatDTO = new ZahtevZaSertifikatDTO();
        zahtevZaSertifikatDTO.konverzija(temp);
        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatMapper.toModel(zahtevZaSertifikatDTO);
        zahtevZaSertifikat.setPrihvacen(false);
        zahtevZaSertifikat.setPotvrdjenZahtev(false);
        zahtevZaSertifikatService.create(zahtevZaSertifikat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/updateZahtevZaSertifikat")
    public ResponseEntity<?> updateZahtevZaSertifikat(@RequestBody ZahtevZaSertifikatDTO zahtevZaSertifikatDTO){
        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatMapper.toModel(zahtevZaSertifikatDTO);
        zahtevZaSertifikatService.update(zahtevZaSertifikat,zahtevZaSertifikat.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dajZahtevZaSertifikat/{id}")
    public ResponseEntity<ZahtevZaSertifikatShortDTO> dajZahtevZaSertifikat(@PathVariable Integer id){
        ZahtevZaSertifikat zahtevZaSertifikat  = zahtevZaSertifikatService.findOne(id);
        return new ResponseEntity<>(new ZahtevZaSertifikatShortDTO(zahtevZaSertifikat.getId(),zahtevZaSertifikat.getStartDate(),zahtevZaSertifikat.getEndDate(),zahtevZaSertifikat.getNamena(),zahtevZaSertifikat.getEmailPotvrda(),zahtevZaSertifikat.getPotvrdjenZahtev(),zahtevZaSertifikat.getPrihvacen()),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dajListuZahtevaZaSertifikat")
    public ResponseEntity<List<ZahtevZaSertifikatShortDTO>> dajListuZahtevaZaSertifikat(){
        List<ZahtevZaSertifikat> lista = zahtevZaSertifikatService.findAll();
        List<ZahtevZaSertifikatShortDTO>listaDTO = new ArrayList<>();
        for (ZahtevZaSertifikat zahtevZaSertifikat : lista)
            listaDTO.add(new ZahtevZaSertifikatShortDTO(zahtevZaSertifikat.getId(),zahtevZaSertifikat.getStartDate(),zahtevZaSertifikat.getEndDate(),zahtevZaSertifikat.getNamena(),zahtevZaSertifikat.getEmailPotvrda(),zahtevZaSertifikat.getPotvrdjenZahtev(),zahtevZaSertifikat.getPrihvacen()));
        return new ResponseEntity<>(listaDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/izbrisiZahtevZaSertifikat/{id}")
    public ResponseEntity<Void> izbrisiZahtevZaSertifikat(@PathVariable Integer id){
        zahtevZaSertifikatService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public SertifikatController() {
        zahtevZaSertifikatMapper = new ZahtevZaSertifikatMapper();
        sertifikatMapper = new SertifikatMapper();
        povlacenjeSertifikataMapper = new PovlacenjeSertifikataMapper();
    }
}
