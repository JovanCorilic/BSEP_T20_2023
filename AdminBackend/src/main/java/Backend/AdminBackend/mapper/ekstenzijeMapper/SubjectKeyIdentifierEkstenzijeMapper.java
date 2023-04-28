package Backend.AdminBackend.mapper.ekstenzijeMapper;

import Backend.AdminBackend.dto.ekstenzije.SubjectKeyIdentifierEkstenzijeDTO;
import Backend.AdminBackend.mapper.MapperInterface;
import Backend.AdminBackend.model.ekstenzije.SubjectKeyIdentifierEkstenzije;

public class SubjectKeyIdentifierEkstenzijeMapper implements MapperInterface<SubjectKeyIdentifierEkstenzije, SubjectKeyIdentifierEkstenzijeDTO> {
    @Override
    public SubjectKeyIdentifierEkstenzije toModel(SubjectKeyIdentifierEkstenzijeDTO dto) {
        if (dto == null)
            return null;
        return new SubjectKeyIdentifierEkstenzije(dto.isDaLiKoristi(),dto.isDaLiJeKriticno());
    }

    @Override
    public SubjectKeyIdentifierEkstenzijeDTO toDto(SubjectKeyIdentifierEkstenzije entity) {
        if (entity == null)
            return null;
        return new SubjectKeyIdentifierEkstenzijeDTO(entity.isDaLiKoristi(),entity.isCritical());
    }
}
