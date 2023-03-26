package Backend.AdminBackend.dto;

public class ZaUredjajDTO {
    private Integer id;
    private String naziv;
    private String svrha;
    private String serijskiBroj;

    public ZaUredjajDTO() {
    }

    public ZaUredjajDTO(Integer id, String naziv, String svrha, String serijskiBroj) {
        this.id = id;
        this.naziv = naziv;
        this.svrha = svrha;
        this.serijskiBroj = serijskiBroj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getSvrha() {
        return svrha;
    }

    public void setSvrha(String svrha) {
        this.svrha = svrha;
    }

    public String getSerijskiBroj() {
        return serijskiBroj;
    }

    public void setSerijskiBroj(String serijskiBroj) {
        this.serijskiBroj = serijskiBroj;
    }
}
