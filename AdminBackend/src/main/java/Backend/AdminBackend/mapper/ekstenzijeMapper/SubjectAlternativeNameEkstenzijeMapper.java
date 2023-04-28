package Backend.AdminBackend.mapper.ekstenzijeMapper;

import Backend.AdminBackend.dto.ekstenzije.AlternativeNameDTO;
import Backend.AdminBackend.dto.ekstenzije.SubjectAlternativeNameEkstenzijeDTO;
import Backend.AdminBackend.mapper.MapperInterface;
import Backend.AdminBackend.model.ekstenzije.AlternativeName;
import Backend.AdminBackend.model.ekstenzije.SubjectAlternativeNameEkstenzije;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SubjectAlternativeNameEkstenzijeMapper implements MapperInterface<SubjectAlternativeNameEkstenzije, SubjectAlternativeNameEkstenzijeDTO> {
    private final AlternativeNameMapper alternativeNameMapper;

    @Override
    public SubjectAlternativeNameEkstenzije toModel(SubjectAlternativeNameEkstenzijeDTO dto) {
        if (dto == null)
            return null;
        Set<AlternativeName>nameSet = new HashSet<>();
        for (AlternativeNameDTO nameDTO : dto.getAlternativeNames()){
            nameSet.add(alternativeNameMapper.toModel(nameDTO));
        }
        return new SubjectAlternativeNameEkstenzije(dto.isDaLiKoristi(),dto.isDaLiJeKriticno(),nameSet);
    }

    @Override
    public SubjectAlternativeNameEkstenzijeDTO toDto(SubjectAlternativeNameEkstenzije entity) {
        if (entity == null)
            return null;
        List<AlternativeNameDTO>lista = new ArrayList<>();
        for (AlternativeName name : entity.getAlternativeNames()){
            lista.add(alternativeNameMapper.toDto(name));
        }
        return new SubjectAlternativeNameEkstenzijeDTO(entity.isDaLiKoristi(),entity.isCritical(),lista);
    }

    public SubjectAlternativeNameEkstenzijeMapper() {
        this.alternativeNameMapper = new AlternativeNameMapper();
    }
}
