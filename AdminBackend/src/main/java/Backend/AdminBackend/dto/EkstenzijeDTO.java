package Backend.AdminBackend.dto;

import Backend.AdminBackend.dto.ekstenzije.*;
import Backend.AdminBackend.model.Sertifikat;
import Backend.AdminBackend.model.ZahtevZaSertifikat;
import Backend.AdminBackend.model.ekstenzije.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EkstenzijeDTO {

    private AuthorityKeyIdentifierEkstenzijeDTO authorityKeyIdentifierEkstenzije;

    private BasicConstraintsEkstenzijeDTO basicConstraintsEkstenzije;

    private ExtendedKeyUsageEkstenzijeDTO extendedKeyUsageEkstenzije;

    private KeyUsageEkstenzijeDTO keyUsageEkstenzije;

    private SubjectAlternativeNameEkstenzijeDTO subjectAlternativeNameEkstenzije;

    private SubjectKeyIdentifierEkstenzijeDTO subjectKeyIdentifierEkstenzije;

    public boolean proveraPodataka(){
        if (basicConstraintsEkstenzije!=null){
            if (basicConstraintsEkstenzije.isDaLiKoristi())
                if (basicConstraintsEkstenzije.proveraPodataka())
                    return true;
        }
        if (subjectAlternativeNameEkstenzije!=null){
            if (subjectAlternativeNameEkstenzije.isDaLiKoristi())
                if (subjectAlternativeNameEkstenzije.proveraPodataka())
                    return true;
        }
        return false;
    }
}
