package Backend.AdminBackend.controller;

import Backend.AdminBackend.dto.TokenDTO;
import Backend.AdminBackend.dto.UserLoginDTO;
import Backend.AdminBackend.dto.UserTokenStateDTO;
import Backend.AdminBackend.dto.ZahtevZaSertifikatDTO;
import Backend.AdminBackend.mapper.KorisnikMapper;
import Backend.AdminBackend.mapper.ZahtevZaSertifikatMapper;
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

import java.util.LinkedHashMap;


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

    @PutMapping(value = "/potvrdaZahteva/{token}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> potvrdaZahteva(@RequestBody TokenDTO tokenDTO) throws Exception{
        try {
            zahtevZaSertifikatService.potvrdaZahteva(tokenDTO.getToken());
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "/log-out", produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> logoutUser() throws Exception {
        //try{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        SecurityContextHolder.clearContext();
        /*}catch (Exception e){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }*/

        return new ResponseEntity<>("You successfully logged out!", HttpStatus.OK);
        /*
        if (!(auth instanceof AnonymousAuthenticationToken)){
            SecurityContextHolder.clearContext();
            return new ResponseEntity<>("You successfully logged out!", HttpStatus.OK);
        } else {
            throw new Exception("User is not authenticated!");
        }*/

    }

    @RequestMapping(value = "/createZahtevZaSertifikat", method=RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createZahtevZaSertifikat(@RequestBody Object zahtevZaSertifikatDTO){
        //ZahtevZaSertifikatDTO temp = (ZahtevZaSertifikatDTO)zahtevZaSertifikatDTO;
        System.out.println(zahtevZaSertifikatDTO);
        //LinkedHashMap<String,LinkedHashMap<String,Object>>lista = (LinkedHashMap<String, LinkedHashMap<String, Object>>)zahtevZaSertifikatDTO;
        //System.out.println((String) lista.get("zahtev").get("endDate"));
        //System.out.println(((LinkedHashMap<String,LinkedHashMap<String,String>>) lista.get("zahtev").get("zaMojaKucaAplikacija")).get("serijskiBroj"));
        ZahtevZaSertifikatMapper zahtevZaSertifikatMapper = new ZahtevZaSertifikatMapper();
        //ZahtevZaSertifikat zahtevZaSertifikat = zahtevZaSertifikatMapper.toModel(zahtevZaSertifikatDTO);
        /*zahtevZaSertifikat.setPrihvacen(false);
        zahtevZaSertifikat.setPotvrdjenZahtev(false);*
        zahtevZaSertifikatService.create(zahtevZaSertifikat);*/
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public AuthenticationController() {
    }
}
