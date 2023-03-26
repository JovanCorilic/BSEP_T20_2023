package Backend.AdminBackend.dto;

public class ZaKorisnikaDTO {
    private Integer id;
    private String email;
    private String ime;
    private String prezime;

    public ZaKorisnikaDTO() {
    }

    public ZaKorisnikaDTO(Integer id, String email, String ime, String prezime) {
        this.id = id;
        this.email = email;
        this.ime = ime;
        this.prezime = prezime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }
}
