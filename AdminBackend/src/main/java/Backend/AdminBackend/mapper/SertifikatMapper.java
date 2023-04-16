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
            case "Uredjaj":
                zaUredjajDTO = zaUredjajMapper.toDto(entity.getZaUredjaj());
                zaUredjajDTO.setId(null);
                break;
            case "Moja kuca aplikacija":
                zaMojaKucaAplikacijaDTO = zaMojaKucaAplikacijaMapper.toDto(entity.getZaMojaKucaAplikacija());
                zaMojaKucaAplikacijaDTO.setId(null);
                break;
            case "Korisnik":
                zaKorisnikaDTO = zaKorisnikaMapper.toDto(entity.getZaKorisnika());
                zaKorisnikaDTO.setId(null);
                break;
        }
        KorisnikDTO korisnikDTO = korisnikMapper.toDto(entity.getKorisnik());

        return new SertifikatDTO(entity.getAlias(), entity.getNamena(), entity.getStartDate(),entity.getEndDate(),
                entity.getSubjectEmail(),korisnikDTO,zaKorisnikaDTO,zaUredjajDTO,zaMojaKucaAplikacijaDTO);
    }

    public SertifikatMapper() {
        this.zaKorisnikaMapper = new ZaKorisnikaMapper();
        this.zaMojaKucaAplikacijaMapper = new ZaMojaKucaAplikacijaMapper();
        this.zaUredjajMapper = new ZaUredjajMapper();
        this.korisnikMapper = new KorisnikMapper();
    }
}
