package Backend.AdminBackend.model;

import lombok.Getter;

@Getter
public enum UlogaEnum {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_SUPERADMIN("ROLE_SUPERADMIN"),
    ROLE_MUSTERIJA("ROLE_MUSTERIJA");

    private String ulogaNaziv;

    UlogaEnum(String ulogaNaziv) {
        this.ulogaNaziv = ulogaNaziv;
    }


    @Override
    public String toString() {
        return ulogaNaziv;
    }
}
