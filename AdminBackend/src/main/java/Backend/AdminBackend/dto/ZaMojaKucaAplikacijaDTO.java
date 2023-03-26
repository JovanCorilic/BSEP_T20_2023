package Backend.AdminBackend.dto;

public class ZaMojaKucaAplikacijaDTO {
    private Integer id;
    private String serijskiBroj;

    public ZaMojaKucaAplikacijaDTO() {
    }

    public ZaMojaKucaAplikacijaDTO(Integer id, String serijskiBroj) {
        this.id = id;
        this.serijskiBroj = serijskiBroj;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSerijskiBroj() {
        return serijskiBroj;
    }

    public void setSerijskiBroj(String serijskiBroj) {
        this.serijskiBroj = serijskiBroj;
    }
}
