package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.KorisnikDTO;
import Backend.AdminBackend.model.Korisnik;

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
