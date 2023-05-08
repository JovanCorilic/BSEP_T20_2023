package Backend.AdminBackend.controller;

import Backend.AdminBackend.dto.ZahtevZaSertifikatDTO;
import Backend.AdminBackend.mapper.ZahtevZaSertifikatMapper;
import Backend.AdminBackend.model.ZahtevZaSertifikat;
import Backend.AdminBackend.service.ZahtevZaSertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
//@CrossOrigin
@RequestMapping(value = "/zahtevZaSertifikat", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZahtevZaSertifikatController {
    @Autowired
    private ZahtevZaSertifikatService zahtevZaSertifikatService;

    private final ZahtevZaSertifikatMapper zahtevZaSertifikatMapper;

    public ZahtevZaSertifikatController() {
        this.zahtevZaSertifikatMapper = new ZahtevZaSertifikatMapper();
    }

    //@PreAuthorize("hasAuthority('OPERACIJE_ZAHTEVA_ZA_SERTIFIKAT_MUSTERIJA')")
    @PutMapping("/potvrdaZahteva/{token}")
    public ResponseEntity<Void> potvrdaZahteva(@PathVariable String token){
        try {
            zahtevZaSertifikatService.potvrdaZahteva(token);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasAuthority('OPERACIJE_ZAHTEVA_ZA_SERTIFIKAT_MUSTERIJA')")
    @PostMapping("/createZahtevZaSertifikat")
    public ResponseEntity<?> createZahtevZaSertifikat(@RequestBody ZahtevZaSertifikatDTO zahtevZaSertifikatDTO){
        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatMapper.toModel(zahtevZaSertifikatDTO);
        zahtevZaSertifikat.setPrihvacen(false);
        zahtevZaSertifikat.setPotvrdjenZahtev(false);
        zahtevZaSertifikatService.create(zahtevZaSertifikat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('OPERACIJE_ZAHTEVA_ZA_SERTIFIKAT_MUSTERIJA')")
    @PutMapping("/updateZahtevZaSertifikat")
    public ResponseEntity<?> updateZahtevZaSertifikat(@RequestBody ZahtevZaSertifikatDTO zahtevZaSertifikatDTO){
        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatMapper.toModel(zahtevZaSertifikatDTO);
        zahtevZaSertifikatService.update(zahtevZaSertifikat,zahtevZaSertifikat.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('OPERACIJE_SERTIFIKATA_ADMIN')")
    @GetMapping("/dajZahtevZaSertifikat/{id}")
    public ResponseEntity<ZahtevZaSertifikatDTO> dajZahtevZaSertifikat(@PathVariable Integer id){
        ZahtevZaSertifikat zahtevZaSertifikat  = zahtevZaSertifikatService.findOne(id);
        return new ResponseEntity<>(zahtevZaSertifikatMapper.toDto(zahtevZaSertifikat),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('OPERACIJE_ZAHTEVA_ZA_SERTIFIKAT_MUSTERIJA')")
    @GetMapping("/dajZahtevZaSertifikatMusterija/{id}")
    public ResponseEntity<ZahtevZaSertifikatDTO> dajZahtevZaSertifikatMusterija(@PathVariable Integer id){
        ZahtevZaSertifikat zahtevZaSertifikat  = zahtevZaSertifikatService.findOneMusterija(id);
        return new ResponseEntity<>(zahtevZaSertifikatMapper.toDto(zahtevZaSertifikat),HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('OPERACIJE_ZAHTEVA_ZA_SERTIFIKAT_MUSTERIJA')")
    @GetMapping("/dajListuZahtevaZaSertifikatMoji")
    public ResponseEntity<List<ZahtevZaSertifikatDTO>> dajListuZahtevaZaSertifikatMoji(){
        List<ZahtevZaSertifikat> lista = zahtevZaSertifikatService.findAllZaMusteriju();
        List<ZahtevZaSertifikatDTO>listaDTO = new ArrayList<>();
        for (ZahtevZaSertifikat zahtevZaSertifikat : lista)
            listaDTO.add(zahtevZaSertifikatMapper.toDto(zahtevZaSertifikat));
        return new ResponseEntity<>(listaDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('OPERACIJE_SERTIFIKATA_ADMIN')")
    @GetMapping("/dajListuZahtevaZaSertifikat")
    public ResponseEntity<List<ZahtevZaSertifikatDTO>> dajListuZahtevaZaSertifikat(){
        List<ZahtevZaSertifikat> lista = zahtevZaSertifikatService.findAll();
        List<ZahtevZaSertifikatDTO>listaDTO = new ArrayList<>();
        for (ZahtevZaSertifikat zahtevZaSertifikat : lista)
            listaDTO.add(zahtevZaSertifikatMapper.toDto(zahtevZaSertifikat));
        return new ResponseEntity<>(listaDTO, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('OPERACIJE_SERTIFIKATA_ADMIN')")
    @DeleteMapping("/izbrisiZahtev/{id}")
    public ResponseEntity<?> izbrisiZahtev(@PathVariable Integer id){
        zahtevZaSertifikatService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
