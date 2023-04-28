package Backend.AdminBackend.dto.ekstenzije;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedKeyUsageEkstenzijeDTO {
    private boolean daLiKoristi;

    private boolean daLiJeKriticno;

    private boolean anyExtendedKeyUsage;

    //Code signing

    private boolean id_kp_codeSigning;
    //Email protection

    private boolean id_kp_emailProtection;
    //IP Security End System
    private boolean id_kp_ipsecEndSystem;
    //IP Security Tunnel Termination ????
    //private boolean id_kp_ipsecTunnel;
    //IP Security User
    private boolean id_kp_ipsecUser;
    //Time Stamping
    private boolean id_kp_timeStamping;
    //OCSP Signing
    private boolean id_kp_OCSPSigning;
    //Smartcard logon
    private boolean id_kp_smartcardlogon;
    //private boolean id_kp_serverAuth; Nisam siguran za ove
    //private boolean id_kp_clientAuth;

}
