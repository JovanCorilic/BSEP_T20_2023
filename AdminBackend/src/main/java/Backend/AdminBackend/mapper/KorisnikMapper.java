package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.KorisnikDTO;
import Backend.AdminBackend.dto.MusterijaDTO;
import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.model.Uloga;

import java.util.ArrayList;
import java.util.List;

public class KorisnikMapper implements MapperInterface<Korisnik, KorisnikDTO> {
    @Override
    public Korisnik toModel(KorisnikDTO dto) {
        return null;
    }

    @Override
    public KorisnikDTO toDto(Korisnik entity) {
        return new KorisnikDTO(entity.getIme(),entity.getPrezime(),entity.getEmail());
    }
}
