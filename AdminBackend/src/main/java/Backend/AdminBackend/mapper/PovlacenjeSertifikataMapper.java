package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.PovlacenjeSertifikataDTO;
import Backend.AdminBackend.model.PovlacenjeSertifikata;

public class PovlacenjeSertifikataMapper implements MapperInterface<PovlacenjeSertifikata, PovlacenjeSertifikataDTO> {
    @Override
    public PovlacenjeSertifikata toModel(PovlacenjeSertifikataDTO dto) {
        return new PovlacenjeSertifikata(null,dto.getRazlog(), dto.getAlias());
    }

    @Override
    public PovlacenjeSertifikataDTO toDto(PovlacenjeSertifikata entity) {
        return new PovlacenjeSertifikataDTO(entity.getRazlog(),entity.getAlias());
    }
}
