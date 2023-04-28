package Backend.AdminBackend.mapper.ekstenzijeMapper;

import Backend.AdminBackend.dto.ekstenzije.AuthorityKeyIdentifierEkstenzijeDTO;
import Backend.AdminBackend.mapper.MapperInterface;
import Backend.AdminBackend.model.ekstenzije.AuthorityKeyIdentifierEkstenzije;

public class AuthorityKeyIdentifierEkstenzijeMapper implements MapperInterface<AuthorityKeyIdentifierEkstenzije, AuthorityKeyIdentifierEkstenzijeDTO> {
    @Override
    public AuthorityKeyIdentifierEkstenzije toModel(AuthorityKeyIdentifierEkstenzijeDTO dto) {
        if (dto == null)
            return null;
        return new AuthorityKeyIdentifierEkstenzije(dto.isDaLiKoristi(),dto.isDaLiJeKriticno());
    }

    @Override
    public AuthorityKeyIdentifierEkstenzijeDTO toDto(AuthorityKeyIdentifierEkstenzije entity) {
        if (entity == null)
            return null;
        return new AuthorityKeyIdentifierEkstenzijeDTO(entity.isDaLiKoristi(),entity.isCritical());
    }
}
