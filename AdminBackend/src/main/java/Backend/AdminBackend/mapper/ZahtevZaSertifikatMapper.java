package Backend.AdminBackend.mapper;

import Backend.AdminBackend.dto.ZaKorisnikaDTO;
import Backend.AdminBackend.dto.ZaMojaKucaAplikacijaDTO;
import Backend.AdminBackend.dto.ZaUredjajDTO;
import Backend.AdminBackend.dto.ZahtevZaSertifikatDTO;
import Backend.AdminBackend.model.ZaKorisnika;
import Backend.AdminBackend.model.ZaMojaKucaAplikacija;
import Backend.AdminBackend.model.ZaUredjaj;
import Backend.AdminBackend.model.ZahtevZaSertifikat;

public class ZahtevZaSertifikatMapper implements MapperInterface<ZahtevZaSertifikat, ZahtevZaSertifikatDTO> {
    private final ZaKorisnikaMapper zaKorisnikaMapper;
    private final ZaMojaKucaAplikacijaMapper zaMojaKucaAplikacijaMapper;
    private final ZaUredjajMapper zaUredjajMapper;
    private final EkstenzijeMapper ekstenzijeMapper;
    private final KorisnikMapper korisnikMapper;
    @Override
    public ZahtevZaSertifikat toModel(ZahtevZaSertifikatDTO dto) {
        ZaKorisnika zaKorisnika = null;
        ZaMojaKucaAplikacija zaMojaKucaAplikacija = null;
        ZaUredjaj zaUredjaj = null;

        switch (dto.getNamena()) {
            case "Uredjaj":
                zaUredjaj = zaUredjajMapper.toModel(dto.getZaUredjaj());
                break;
            case "Moja kuca aplikacija":
                zaMojaKucaAplikacija = zaMojaKucaAplikacijaMapper.toModel(dto.getZaMojaKucaAplikacija());
                break;
            case "Korisnik":
                zaKorisnika = zaKorisnikaMapper.toModel(dto.getZaKorisnika());
                break;
        }

        return new ZahtevZaSertifikat(dto.getId(),dto.getStartDate(),dto.getEndDate(),dto.getNamena(),
                dto.getEmailPotvrda(),dto.getPotvrdjenZahtev(),dto.getPrihvacen(),zaKorisnika,zaUredjaj,
                zaMojaKucaAplikacija,korisnikMapper.toModel(dto.getMusterija()),korisnikMapper.toModel(dto.getAdmin()),
                ekstenzijeMapper.toModel(dto.getEkstenzije()));
    }

    @Override
    public ZahtevZaSertifikatDTO toDto(ZahtevZaSertifikat entity) {
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
        return new ZahtevZaSertifikatDTO(entity.getId(),entity.getStartDate(),entity.getEndDate(),entity.getNamena(),
                entity.getEmailPotvrda(),entity.getPotvrdjenZahtev(),entity.getPrihvacen(),zaKorisnikaDTO,zaUredjajDTO,
                zaMojaKucaAplikacijaDTO,korisnikMapper.toDto(entity.getMusterija()),korisnikMapper.toDto(entity.getAdmin()),
                ekstenzijeMapper.toDto(entity.getEkstenzije()));
    }

    public ZahtevZaSertifikatMapper() {
        this.zaKorisnikaMapper = new ZaKorisnikaMapper();
        this.zaMojaKucaAplikacijaMapper = new ZaMojaKucaAplikacijaMapper();
        this.zaUredjajMapper = new ZaUredjajMapper();
        this.ekstenzijeMapper = new EkstenzijeMapper();
        this.korisnikMapper = new KorisnikMapper();
    }
}
