package Backend.AdminBackend.mapper.ekstenzijeMapper;

import Backend.AdminBackend.dto.ekstenzije.KeyUsageEkstenzijeDTO;
import Backend.AdminBackend.mapper.MapperInterface;
import Backend.AdminBackend.model.ekstenzije.KeyUsageEkstenzije;

public class KeyUsageEkstenzijeMapper implements MapperInterface<KeyUsageEkstenzije, KeyUsageEkstenzijeDTO> {
    @Override
    public KeyUsageEkstenzije toModel(KeyUsageEkstenzijeDTO dto) {
        if (dto == null)
            return null;
        return new KeyUsageEkstenzije(dto.isDaLiKoristi(),dto.isDaLiJeKriticno(),dto.isDigitalSignature(),
                dto.isNonRepudiation(),dto.isKeyEncipherment(),dto.isDataEncipherment(),dto.isKeyAgreement(),
                dto.isKeyCertSign(),dto.isDa_li_jecrlsign(),dto.isEncipherOnly(),dto.isDecipherOnly());
    }

    @Override
    public KeyUsageEkstenzijeDTO toDto(KeyUsageEkstenzije entity) {
        if (entity == null)
            return null;
        return new KeyUsageEkstenzijeDTO(entity.isDaLiKoristi(),entity.isCritical(),entity.isDigitalSignature(),
                entity.isNonRepudiation(),entity.isKeyEncipherment(),entity.isDataEncipherment(),
                entity.isKeyAgreement(),entity.isKeyCertSign(),entity.isCRLSign(),entity.isEncipherOnly(),
                entity.isDecipherOnly());
    }
}
