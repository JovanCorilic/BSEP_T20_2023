package Backend.AdminBackend.mapper.ekstenzijeMapper;

import Backend.AdminBackend.dto.ekstenzije.ExtendedKeyUsageEkstenzijeDTO;
import Backend.AdminBackend.mapper.MapperInterface;
import Backend.AdminBackend.model.ekstenzije.ExtendedKeyUsageEkstenzije;

public class ExtendedKeyUsageEkstenzijeMapper implements MapperInterface<ExtendedKeyUsageEkstenzije, ExtendedKeyUsageEkstenzijeDTO> {
    @Override
    public ExtendedKeyUsageEkstenzije toModel(ExtendedKeyUsageEkstenzijeDTO dto) {
        return new ExtendedKeyUsageEkstenzije(dto.isDaLiKoristi(),dto.isCritical(),dto.isAnyExtendedKeyUsage(),dto.isId_kp_codeSigning(),
                dto.isId_kp_emailProtection(),dto.isId_kp_ipsecEndSystem(),dto.isId_kp_ipsecUser(),dto.isId_kp_timeStamping(),
                dto.isId_kp_OCSPSigning(),dto.isId_kp_smartcardlogon());
    }

    @Override
    public ExtendedKeyUsageEkstenzijeDTO toDto(ExtendedKeyUsageEkstenzije entity) {
        return new ExtendedKeyUsageEkstenzijeDTO(entity.isDaLiKoristi(),entity.isCritical(),
                entity.isAnyExtendedKeyUsage(),entity.isId_kp_codeSigning(),entity.isId_kp_emailProtection(),
                entity.isId_kp_ipsecEndSystem(),entity.isId_kp_ipsecUser(),entity.isId_kp_timeStamping(),
                entity.isId_kp_OCSPSigning(),entity.isId_kp_smartcardlogon());
    }
}
