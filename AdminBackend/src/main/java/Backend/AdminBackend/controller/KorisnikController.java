package Backend.AdminBackend.controller;

import Backend.AdminBackend.dto.MusterijaDTO;
import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/korisnik", produces = MediaType.APPLICATION_JSON_VALUE)
public class KorisnikController {
    @Autowired
    private CustomUserDetailsService userDetailsService;

    @PreAuthorize("hasRole('ROLE_SUPERADMIN')")
    @PostMapping("/napraviAdmina")
    public ResponseEntity<?> napraviAdmina(@RequestBody MusterijaDTO musterijaDTO){
        userDetailsService.pravljenjeAdminNaloga(musterijaDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/napraviMusteriju/{email}")
    public ResponseEntity<?> potvrdiMusterijuOdStraneAdmina(@PathVariable String email){
        userDetailsService.potvrdiMusteriju(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/napraviMusteriju")
    public ResponseEntity<List<Korisnik>> SveMusterije(){
        List<Korisnik>lista = userDetailsService.SveMusterije();
        return new ResponseEntity<>(lista,HttpStatus.OK);
    }
}
