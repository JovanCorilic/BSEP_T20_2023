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

@RestController
@CrossOrigin
@RequestMapping(value = "/zahtevZaSertifikat", produces = MediaType.APPLICATION_JSON_VALUE)
public class ZahtevZaSertifikatController {
    @Autowired
    private ZahtevZaSertifikatService zahtevZaSertifikatService;

    private final ZahtevZaSertifikatMapper zahtevZaSertifikatMapper;

    public ZahtevZaSertifikatController() {
        this.zahtevZaSertifikatMapper = new ZahtevZaSertifikatMapper();
    }

    @PreAuthorize("hasRole('ROLE_MUSTERIJA')")
    @PutMapping("/potvrdaZahteva/{token}")
    public ResponseEntity<Void> potvrdaZahteva(@PathVariable String token){
        try {
            zahtevZaSertifikatService.potvrdaZahteva(token);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasRole('ROLE_MUSTERIJA')")
    @PostMapping("/createZahtevZaSertifikat")
    public ResponseEntity<?> createZahtevZaSertifikat(@RequestBody ZahtevZaSertifikatDTO zahtevZaSertifikatDTO){
        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatMapper.toModel(zahtevZaSertifikatDTO);
        zahtevZaSertifikat.setPrihvacen(false);
        zahtevZaSertifikat.setPotvrdjenZahtev(false);
        zahtevZaSertifikatService.create(zahtevZaSertifikat);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MUSTERIJA')")
    @PutMapping("/updateZahtevZaSertifikat")
    public ResponseEntity<?> updateZahtevZaSertifikat(@RequestBody ZahtevZaSertifikatDTO zahtevZaSertifikatDTO){
        ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatMapper.toModel(zahtevZaSertifikatDTO);
        zahtevZaSertifikatService.update(zahtevZaSertifikat,zahtevZaSertifikat.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAnyRole('ROLE_MUSTERIJA','ROLE_ADMIN')")
    @GetMapping("/dajZahtevZaSertifikat/{id}")
    public ResponseEntity<ZahtevZaSertifikatDTO> dajZahtevZaSertifikat(@PathVariable Integer id){
        ZahtevZaSertifikat zahtevZaSertifikat  = zahtevZaSertifikatService.findOne(id);
        return new ResponseEntity<>(zahtevZaSertifikatMapper.toDto(zahtevZaSertifikat),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_MUSTERIJA')")
    @GetMapping("/dajListuZahtevaZaSertifikatMoji")
    public ResponseEntity<List<ZahtevZaSertifikatDTO>> dajListuZahtevaZaSertifikatMoji(){
        List<ZahtevZaSertifikat> lista = zahtevZaSertifikatService.findAll();
        List<ZahtevZaSertifikatDTO>listaDTO = new ArrayList<>();
        for (ZahtevZaSertifikat zahtevZaSertifikat : lista)
            listaDTO.add(zahtevZaSertifikatMapper.toDto(zahtevZaSertifikat));
        return new ResponseEntity<>(listaDTO, HttpStatus.OK);
    }


}
