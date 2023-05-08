package Backend.AdminBackend.controller;

import Backend.AdminBackend.dto.KorisnikDTO;
import Backend.AdminBackend.dto.MusterijaDTO;
import Backend.AdminBackend.mapper.KorisnikMapper;
import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@CrossOrigin
@RequestMapping(value = "/korisnik", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    private final KorisnikMapper korisnikMapper;

    @PreAuthorize("hasAuthority('RAD_SA_ADMINOM')")
    @PostMapping("/napraviAdmina")
    public ResponseEntity<?> napraviAdmina(@RequestBody MusterijaDTO musterijaDTO){
        if (musterijaDTO.proveraPodataka())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        userDetailsService.pravljenjeAdminNaloga(musterijaDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RAD_SA_MUSTERIJOM')")
    @GetMapping("/napraviMusteriju/{email}")
    public ResponseEntity<?> potvrdiMusterijuOdStraneAdmina(@PathVariable String email){
        userDetailsService.potvrdiMusteriju(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RAD_SA_MUSTERIJOM')")
    @DeleteMapping("/izbrisiMusteriju/{email}")
    public ResponseEntity<?> izbrisiMusteriju(@PathVariable String email){
        userDetailsService.delete(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('RAD_SA_MUSTERIJOM')")
    @GetMapping("/sveMusterije")
    public ResponseEntity<List<KorisnikDTO>> SveMusterije(){
        List<Korisnik>lista = userDetailsService.SveMusterije();
        List<KorisnikDTO>listaDTO = new ArrayList<>();
        for (Korisnik korisnik : lista){
            listaDTO.add(korisnikMapper.toDto(korisnik));
        }
        return new ResponseEntity<>(listaDTO,HttpStatus.OK);
    }

    public KorisnikController() {
        this.korisnikMapper = new KorisnikMapper();
    }
}
