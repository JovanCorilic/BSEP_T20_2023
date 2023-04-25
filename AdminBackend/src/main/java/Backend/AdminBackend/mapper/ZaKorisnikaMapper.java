package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.ZaKorisnikaDTO;
import Backend.AdminBackend.model.ZaKorisnika;

public class ZaKorisnikaMapper implements MapperInterface<ZaKorisnika, ZaKorisnikaDTO> {
    @Override
    public ZaKorisnika toModel(ZaKorisnikaDTO dto) {
        return new ZaKorisnika(dto.getEmail(),dto.getIme(),dto.getPrezime());
    }

    @Override
    public ZaKorisnikaDTO toDto(ZaKorisnika entity) {
        return new ZaKorisnikaDTO(entity.getEmail(),entity.getIme(),entity.getPrezime());
    }
}
