package Backend.AdminBackend.dto;


public class PovlacenjeSertifikataDTO {
    private String razlog;
    private String alias;

    public PovlacenjeSertifikataDTO(String razlog, String alias) {
        this.razlog = razlog;
        this.alias = alias;
    }

    public PovlacenjeSertifikataDTO() {
    }

    public String getRazlog() {
        return razlog;
    }

    public void setRazlog(String razlog) {
        this.razlog = razlog;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
