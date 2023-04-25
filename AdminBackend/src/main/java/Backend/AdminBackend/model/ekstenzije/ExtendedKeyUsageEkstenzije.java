package Backend.AdminBackend.model.ekstenzije;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bouncycastle.asn1.x509.KeyPurposeId;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ExtendedKeyUsageEkstenzije {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private boolean daLiKoristi;

    @Column
    private boolean isCritical;

    @Column
    private boolean anyExtendedKeyUsage;

    //Code signing
    @Column
    private boolean id_kp_codeSigning;
    //Email protection
    @Column
    private boolean id_kp_emailProtection;
    //IP Security End System
    @Column
    private boolean id_kp_ipsecEndSystem;
    //IP Security Tunnel Termination ????
    //private boolean id_kp_ipsecTunnel;
    //IP Security User
    @Column
    private boolean id_kp_ipsecUser;
    //Time Stamping
    @Column
    private boolean id_kp_timeStamping;
    //OCSP Signing
    @Column
    private boolean id_kp_OCSPSigning;
    //Smartcard logon
    @Column
    private boolean id_kp_smartcardlogon;

    public ExtendedKeyUsageEkstenzije(boolean daLiKoristi, boolean isCritical, boolean anyExtendedKeyUsage, boolean id_kp_codeSigning, boolean id_kp_emailProtection, boolean id_kp_ipsecEndSystem, boolean id_kp_ipsecUser, boolean id_kp_timeStamping, boolean id_kp_OCSPSigning, boolean id_kp_smartcardlogon) {
        this.daLiKoristi = daLiKoristi;
        this.isCritical = isCritical;
        this.anyExtendedKeyUsage = anyExtendedKeyUsage;
        this.id_kp_codeSigning = id_kp_codeSigning;
        this.id_kp_emailProtection = id_kp_emailProtection;
        this.id_kp_ipsecEndSystem = id_kp_ipsecEndSystem;
        this.id_kp_ipsecUser = id_kp_ipsecUser;
        this.id_kp_timeStamping = id_kp_timeStamping;
        this.id_kp_OCSPSigning = id_kp_OCSPSigning;
        this.id_kp_smartcardlogon = id_kp_smartcardlogon;
    }

    //private boolean id_kp_serverAuth; Nisam siguran za ove
    //private boolean id_kp_clientAuth;
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
