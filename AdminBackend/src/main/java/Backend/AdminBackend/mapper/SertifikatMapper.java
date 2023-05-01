package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.*;
import Backend.AdminBackend.model.Sertifikat;

public class SertifikatMapper implements MapperInterface<Sertifikat, SertifikatDTO> {
    private final ZaKorisnikaMapper zaKorisnikaMapper;
    private final ZaMojaKucaAplikacijaMapper zaMojaKucaAplikacijaMapper;
    private final ZaUredjajMapper zaUredjajMapper;
    private final KorisnikMapper korisnikMapper;
    private final EkstenzijeMapper ekstenzijeMapper;

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

                break;
            case "Moja kuca aplikacija":
                zaMojaKucaAplikacijaDTO = zaMojaKucaAplikacijaMapper.toDto(entity.getZaMojaKucaAplikacija());

                break;
            case "Korisnik":
                zaKorisnikaDTO = zaKorisnikaMapper.toDto(entity.getZaKorisnika());

                break;
        }

        return new SertifikatDTO(entity.getAlias(), entity.getNamena(), entity.getStartDate(),entity.getEndDate(),
                entity.getSubjectEmail(),entity.getOrganizacionaJedinica(),entity.getNazivOrganizacije(),entity.getSkraceniNazivZemlje(),
                zaKorisnikaDTO,zaUredjajDTO,zaMojaKucaAplikacijaDTO,
                korisnikMapper.toDto(entity.getMusterija()),korisnikMapper.toDto(entity.getAdmin()),
                ekstenzijeMapper.toDto(entity.getEkstenzije()));
    }

    public SertifikatMapper() {
        this.zaKorisnikaMapper = new ZaKorisnikaMapper();
        this.zaMojaKucaAplikacijaMapper = new ZaMojaKucaAplikacijaMapper();
        this.zaUredjajMapper = new ZaUredjajMapper();
        this.korisnikMapper = new KorisnikMapper();
        this.ekstenzijeMapper = new EkstenzijeMapper();
    }
}
