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
    private ZaKorisnikaMapper zaKorisnikaMapper;
    private ZaMojaKucaAplikacijaMapper zaMojaKucaAplikacijaMapper;
    private ZaUredjajMapper zaUredjajMapper;
    @Override
    public ZahtevZaSertifikat toModel(ZahtevZaSertifikatDTO dto) {
        ZaKorisnika zaKorisnika = null;
        ZaMojaKucaAplikacija zaMojaKucaAplikacija = null;
        ZaUredjaj zaUredjaj = null;

        switch (dto.getNamena()) {
            case "uredjaj":
                zaUredjaj = zaUredjajMapper.toModel(dto.getZaUredjaj());
                break;
            case "mojakuca":
                zaMojaKucaAplikacija = zaMojaKucaAplikacijaMapper.toModel(dto.getZaMojaKucaAplikacija());
                break;
            case "korisnik":
                zaKorisnika = zaKorisnikaMapper.toModel(dto.getZaKorisnika());
                break;
        }
        return new ZahtevZaSertifikat(dto.getId(),dto.getStartDate(),dto.getEndDate(),dto.getNamena(),
                dto.getEmailPotvrda(),dto.getPotvrdjenZahtev(),dto.getPrihvacen(),zaKorisnika,zaUredjaj,
                zaMojaKucaAplikacija);
    }

    @Override
    public ZahtevZaSertifikatDTO toDto(ZahtevZaSertifikat entity) {
        ZaKorisnikaDTO zaKorisnikaDTO = null;
        ZaMojaKucaAplikacijaDTO zaMojaKucaAplikacijaDTO = null;
        ZaUredjajDTO zaUredjajDTO = null;

        switch (entity.getNamena()) {
            case "uredjaj":
                zaUredjajDTO = zaUredjajMapper.toDto(entity.getZaUredjaj());
                break;
            case "mojakuca":
                zaMojaKucaAplikacijaDTO = zaMojaKucaAplikacijaMapper.toDto(entity.getZaMojaKucaAplikacija());
                break;
            case "korisnik":
                zaKorisnikaDTO = zaKorisnikaMapper.toDto(entity.getZaKorisnika());
                break;
        }
        return new ZahtevZaSertifikatDTO(entity.getId(),entity.getStartDate(),entity.getEndDate(),entity.getNamena(),
                entity.getEmailPotvrda(),entity.getPotvrdjenZahtev(),entity.getPrihvacen(),zaKorisnikaDTO,zaUredjajDTO,
                zaMojaKucaAplikacijaDTO);
    }

    public ZahtevZaSertifikatMapper() {
        this.zaKorisnikaMapper = new ZaKorisnikaMapper();
        this.zaMojaKucaAplikacijaMapper = new ZaMojaKucaAplikacijaMapper();
        this.zaUredjajMapper = new ZaUredjajMapper();
    }
}