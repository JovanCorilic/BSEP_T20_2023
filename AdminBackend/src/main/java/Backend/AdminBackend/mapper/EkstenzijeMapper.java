package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.EkstenzijeDTO;
import Backend.AdminBackend.mapper.ekstenzijeMapper.*;
import Backend.AdminBackend.model.Ekstenzije;

public class EkstenzijeMapper implements MapperInterface<Ekstenzije, EkstenzijeDTO> {
    private final AuthorityKeyIdentifierEkstenzijeMapper authorityKeyIdentifierEkstenzijeMapper;
    private final BasicConstraintsEkstenzijeMapper basicConstraintsEkstenzijeMapper;
    private final ExtendedKeyUsageEkstenzijeMapper extendedKeyUsageEkstenzijeMapper;
    private final KeyUsageEkstenzijeMapper keyUsageEkstenzijeMapper;
    private final SubjectAlternativeNameEkstenzijeMapper subjectAlternativeNameEkstenzijeMapper;
    private final SubjectKeyIdentifierEkstenzijeMapper subjectKeyIdentifierEkstenzijeMapper;

    @Override
    public Ekstenzije toModel(EkstenzijeDTO dto) {
        return new Ekstenzije(authorityKeyIdentifierEkstenzijeMapper.toModel(dto.getAuthorityKeyIdentifierEkstenzije()),
                basicConstraintsEkstenzijeMapper.toModel(dto.getBasicConstraintsEkstenzije()),
                extendedKeyUsageEkstenzijeMapper.toModel(dto.getExtendedKeyUsageEkstenzije()),
                keyUsageEkstenzijeMapper.toModel(dto.getKeyUsageEkstenzije()),
                subjectAlternativeNameEkstenzijeMapper.toModel(dto.getSubjectAlternativeNameEkstenzije()),
                subjectKeyIdentifierEkstenzijeMapper.toModel(dto.getSubjectKeyIdentifierEkstenzije()));
    }

    @Override
    public EkstenzijeDTO toDto(Ekstenzije entity) {
        return new EkstenzijeDTO(authorityKeyIdentifierEkstenzijeMapper.toDto(entity.getAuthorityKeyIdentifierEkstenzije()),
                basicConstraintsEkstenzijeMapper.toDto(entity.getBasicConstraintsEkstenzije()),
                extendedKeyUsageEkstenzijeMapper.toDto(entity.getExtendedKeyUsageEkstenzije()),
                keyUsageEkstenzijeMapper.toDto(entity.getKeyUsageEkstenzije()),
                subjectAlternativeNameEkstenzijeMapper.toDto(entity.getSubjectAlternativeNameEkstenzije()),
                subjectKeyIdentifierEkstenzijeMapper.toDto(entity.getSubjectKeyIdentifierEkstenzije()));
    }

    public EkstenzijeMapper() {
        this.authorityKeyIdentifierEkstenzijeMapper = new AuthorityKeyIdentifierEkstenzijeMapper();
        this.basicConstraintsEkstenzijeMapper = new BasicConstraintsEkstenzijeMapper();
        this.extendedKeyUsageEkstenzijeMapper = new ExtendedKeyUsageEkstenzijeMapper();
        this.keyUsageEkstenzijeMapper = new KeyUsageEkstenzijeMapper();
        this.subjectAlternativeNameEkstenzijeMapper = new SubjectAlternativeNameEkstenzijeMapper();
        this.subjectKeyIdentifierEkstenzijeMapper = new SubjectKeyIdentifierEkstenzijeMapper();
    }
}
