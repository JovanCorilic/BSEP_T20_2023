package Backend.AdminBackend.controller;

import Backend.AdminBackend.dto.MusterijaDTO;
import Backend.AdminBackend.dto.TokenDTO;
import Backend.AdminBackend.dto.UserLoginDTO;
import Backend.AdminBackend.dto.UserTokenStateDTO;
import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.model.ZahtevZaSertifikat;
import Backend.AdminBackend.security.TokenUtils;
import Backend.AdminBackend.service.CustomUserDetailsService;
import Backend.AdminBackend.service.ZahtevZaSertifikatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;


@RestController
@CrossOrigin
@RequestMapping(value = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthenticationController {
    @Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ZahtevZaSertifikatService zahtevZaSertifikatService;

    @PostMapping("/log-in")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody UserLoginDTO authenticationRequest,
                                                       HttpServletResponse response) throws InterruptedException {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Korisnik user = (Korisnik) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getEmail(),user.getUloge().get(0).getAuthority());
        int expiresIn = tokenUtils.getExpiredIn();

        // Vrati token kao odgovor na uspesnu autentifikaciju
        return ResponseEntity.ok(new UserTokenStateDTO(jwt, expiresIn));
    }

    @GetMapping(value = "/log-out", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> logoutUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("You successfully logged out!", HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?>registracijaMusterije(@RequestBody MusterijaDTO musterijaDTO){
        userDetailsService.register(musterijaDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verifikacijaRegistracijaMusterija/{token}")
    public ResponseEntity<?>VerifikacijaRegistracije(@PathVariable String token) throws Exception {
        userDetailsService.potvrdaNaloga(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/verifikacijaAdminNalog/{token}")
    public ResponseEntity<?>VerifikacijaAdminNaloga(@PathVariable String token) throws Exception {
        userDetailsService.potvrdaNaloga(token);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    public AuthenticationController() {
    }
}
