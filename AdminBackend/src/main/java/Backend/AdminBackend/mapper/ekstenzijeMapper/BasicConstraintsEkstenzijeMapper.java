package Backend.AdminBackend.mapper.ekstenzijeMapper;

import Backend.AdminBackend.dto.ekstenzije.BasicConstraintsEkstenzijeDTO;
import Backend.AdminBackend.mapper.MapperInterface;
import Backend.AdminBackend.model.ekstenzije.BasicConstraintsEkstenzije;

public class BasicConstraintsEkstenzijeMapper implements MapperInterface<BasicConstraintsEkstenzije, BasicConstraintsEkstenzijeDTO> {
    @Override
    public BasicConstraintsEkstenzije toModel(BasicConstraintsEkstenzijeDTO dto) {
        return new BasicConstraintsEkstenzije(dto.isDaLiKoristi(),dto.isCritical(),dto.isCA(),dto.getMaxPathLen());
    }

    @Override
    public BasicConstraintsEkstenzijeDTO toDto(BasicConstraintsEkstenzije entity) {
        return new BasicConstraintsEkstenzijeDTO(entity.isDaLiKoristi(),entity.isCritical(),entity.isCA(),entity.getMaxPathLen());
    }
}
