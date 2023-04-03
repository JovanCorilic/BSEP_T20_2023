package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.*;
import Backend.AdminBackend.model.Sertifikat;

public class SertifikatMapper implements MapperInterface<Sertifikat, SertifikatDTO> {
    private ZaKorisnikaMapper zaKorisnikaMapper;
    private ZaMojaKucaAplikacijaMapper zaMojaKucaAplikacijaMapper;
    private ZaUredjajMapper zaUredjajMapper;
    private KorisnikMapper korisnikMapper;

    @Override
    public Sertifikat toModel(SertifikatDTO dto) {
        return null;
    }

    @Override
    public SertifikatDTO toDto(Sertifikat entity) {
        ZaKorisnikaDTO zaKorisnikaDTO = null;
        ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacijaDTO = null;
        ZaUredjajDTO zaUredjajDTO = null;

        switch (entity.getNamena()) {
            case "uredjaj":
                zaUredjajDTO = zaUredjajMapper.toDto(entity.getZaUredjaj());
                zaUredjajDTO.setId(null);
                break;
            case "mojakuca":
                zaMojaKucaAplikacijaDTO = zaMojaKucaAplikacijaMapper.toDto(entity.getZaMojaKucaAplikacija());
                zaMojaKucaAplikacijaDTO.setId(null);
                break;
            case "korisnik":
                zaKorisnikaDTO = zaKorisnikaMapper.toDto(entity.getZaKorisnika());
                zaKorisnikaDTO.setId(null);
                break;
        }
        KorisnikDTO korisnikDTO = korisnikMapper.toDto(entity.getKorisnik());

        return new SertifikatDTO(entity.getAlias(), entity.getNamena(), entity.getStartDate(),entity.getEndDate(),
                entity.getSubjectEmail(),korisnikDTO,zaKorisnikaDTO,zaUredjajDTO,zaMojaKucaAplikacijaDTO);
    }
}
