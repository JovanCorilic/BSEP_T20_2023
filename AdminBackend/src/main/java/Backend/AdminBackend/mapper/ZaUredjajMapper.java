package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.ZaUredjajDTO;
import Backend.AdminBackend.model.ZaUredjaj;

public class ZaUredjajMapper implements MapperInterface<ZaUredjaj, ZaUredjajDTO> {
    @Override
    public ZaUredjaj toModel(ZaUredjajDTO dto) {
        return new ZaUredjaj(dto.getNaziv(),dto.getSvrha(),dto.getSerijskiBroj());
    }

    @Override
    public ZaUredjajDTO toDto(ZaUredjaj entity) {
        return new ZaUredjajDTO(entity.getNaziv(),entity.getSvrha(),entity.getSerijskiBroj());
    }
}
