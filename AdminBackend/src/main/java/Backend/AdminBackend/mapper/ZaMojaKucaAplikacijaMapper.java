package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.ZaMojaKucaAplikacijaDTO;
import Backend.AdminBackend.model.ZaMojaKucaAplikacija;

public class ZaMojaKucaAplikacijaMapper implements MapperInterface<ZaMojaKucaAplikacija, ZaMojaKucaAplikacijaDTO> {
    @Override
    public ZaMojaKucaAplikacija toModel(ZaMojaKucaAplikacijaDTO dto) {
        return new ZaMojaKucaAplikacija(dto.getSerijskiBroj());
    }

    @Override
    public ZaMojaKucaAplikacijaDTO toDto(ZaMojaKucaAplikacija entity) {
        return new ZaMojaKucaAplikacijaDTO(entity.getSerijskiBroj());
    }
}
