package Backend.AdminBackend.mapper.ekstenzijeMapper;

import Backend.AdminBackend.dto.ekstenzije.AlternativeNameDTO;
import Backend.AdminBackend.mapper.MapperInterface;
import Backend.AdminBackend.model.ekstenzije.AlternativeName;

public class AlternativeNameMapper implements MapperInterface<AlternativeName, AlternativeNameDTO> {
    @Override
    public AlternativeName toModel(AlternativeNameDTO dto) {
        return new AlternativeName(dto.getGeneralNameType(), dto.getGeneralNameContent());
    }

    @Override
    public AlternativeNameDTO toDto(AlternativeName entity) {
        return new AlternativeNameDTO(entity.getGeneralNameType(), entity.getGeneralNameContent());
    }
}
