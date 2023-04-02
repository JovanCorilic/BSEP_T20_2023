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
@RequestMapping(value = "/sertifikat", produces = MediaType.APPLICATION_JSON_VALUE)
public class SertifikatController {

    @Autowired
    private ZahtevZaSertifikatService zahtevZaSertifikatService;
    private final ZahtevZaSertifikatMapper zahtevZaSertifikatMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> createSertifikat(){
        System.out.println("Sve uspesno!");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/createZahtevZaSertifikat")
    public ResponseEntity<?> createZahtevZaSertifikat(@RequestBody ZahtevZaSertifikatDTO zahtevZaSertifikatDTO){
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
    public ResponseEntity<ZahtevZaSertifikatDTO> dajZahtevZaSertifikat(@PathVariable Integer id){
        ZahtevZaSertifikat zahtevZaSertifikat  = zahtevZaSertifikatService.findOne(id);
        return new ResponseEntity<>(zahtevZaSertifikatMapper.toDto(zahtevZaSertifikat),HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/dajListuZahtevaZaSertifikat")
    public ResponseEntity<List<ZahtevZaSertifikatDTO>> dajListuZahtevaZaSertifikat(){
        List<ZahtevZaSertifikat> lista = zahtevZaSertifikatService.findAll();
        List<ZahtevZaSertifikatDTO>listaDTO = new ArrayList<>();
        for (ZahtevZaSertifikat zahtevZaSertifikat : lista)
            listaDTO.add(zahtevZaSertifikatMapper.toDto(zahtevZaSertifikat));
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
    }
}
