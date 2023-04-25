package Backend.AdminBackend.mapper.ekstenzijeMapper;

import Backend.AdminBackend.dto.ekstenzije.SubjectKeyIdentifierEkstenzijeDTO;
import Backend.AdminBackend.mapper.MapperInterface;
import Backend.AdminBackend.model.ekstenzije.SubjectKeyIdentifierEkstenzije;

public class SubjectKeyIdentifierEkstenzijeMapper implements MapperInterface<SubjectKeyIdentifierEkstenzije, SubjectKeyIdentifierEkstenzijeDTO> {
    @Override
    public SubjectKeyIdentifierEkstenzije toModel(SubjectKeyIdentifierEkstenzijeDTO dto) {
        return new SubjectKeyIdentifierEkstenzije(dto.isDaLiKoristi(),dto.isCritical());
    }

    @Override
    public SubjectKeyIdentifierEkstenzijeDTO toDto(SubjectKeyIdentifierEkstenzije entity) {
        return new SubjectKeyIdentifierEkstenzijeDTO(entity.isDaLiKoristi(),entity.isCritical());
    }
}
