package Backend.AdminBackend.mapper.ekstenzijeMapper;

import Backend.AdminBackend.dto.ekstenzije.KeyUsageEkstenzijeDTO;
import Backend.AdminBackend.mapper.MapperInterface;
import Backend.AdminBackend.model.ekstenzije.KeyUsageEkstenzije;

public class KeyUsageEkstenzijeMapper implements MapperInterface<KeyUsageEkstenzije, KeyUsageEkstenzijeDTO> {
    @Override
    public KeyUsageEkstenzije toModel(KeyUsageEkstenzijeDTO dto) {
        return new KeyUsageEkstenzije(dto.isDaLiKoristi(),dto.isCritical(),dto.isDigitalSignature(),
                dto.isNonRepudiation(),dto.isKeyEncipherment(),dto.isDataEncipherment(),dto.isKeyAgreement(),
                dto.isKeyCertSign(),dto.isCRLSign(),dto.isEncipherOnly(),dto.isDecipherOnly());
    }

    @Override
    public KeyUsageEkstenzijeDTO toDto(KeyUsageEkstenzije entity) {
        return new KeyUsageEkstenzijeDTO(entity.isDaLiKoristi(),entity.isCritical(),entity.isDigitalSignature(),
                entity.isNonRepudiation(),entity.isKeyEncipherment(),entity.isDataEncipherment(),
                entity.isKeyAgreement(),entity.isKeyCertSign(),entity.isCRLSign(),entity.isEncipherOnly(),
                entity.isDecipherOnly());
    }
}
