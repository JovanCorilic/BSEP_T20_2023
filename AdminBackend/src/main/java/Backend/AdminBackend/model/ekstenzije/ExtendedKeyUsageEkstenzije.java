package Backend.AdminBackend.model.ekstenzije;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.x509.KeyPurposeId;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExtendedKeyUsageEkstenzije {
    private boolean daLiKoristi;
    private boolean isCritical;

    private boolean anyExtendedKeyUsage;
    //private boolean id_kp_serverAuth; Nisam siguran za ove
    //private boolean id_kp_clientAuth;
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
    /*public static final KeyPurposeId id_kp_dvcs;//-
    public static final KeyPurposeId id_kp_sbgpCertAAServerAuth;//-
    public static final KeyPurposeId id_kp_scvp_responder;//-
    public static final KeyPurposeId id_kp_eapOverPPP;//-
    public static final KeyPurposeId id_kp_eapOverLAN;//-
    public static final KeyPurposeId id_kp_scvpServer;//--
    public static final KeyPurposeId id_kp_scvpClient;//--
    public static final KeyPurposeId id_kp_ipsecIKE;//-
    public static final KeyPurposeId id_kp_capwapAC;//-
    public static final KeyPurposeId id_kp_capwapWTP;//-*/

    /*public static final KeyPurposeId id_kp_macAddress;//-
    public static final KeyPurposeId id_kp_msSGC;//--
    public static final KeyPurposeId id_kp_nsSGC;//--*/
}
