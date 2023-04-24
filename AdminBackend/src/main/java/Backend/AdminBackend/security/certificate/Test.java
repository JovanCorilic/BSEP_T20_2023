package Backend.AdminBackend.security.certificate;
import java.math.BigInteger;
import java.security.*;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.x500.X500Name;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;
public class Test {
    public static X509Certificate createSelfSignedCertWithEKU(boolean isCritical, KeyPurposeId... purposes) throws Exception {
        // Generate a public/private key pair
        KeyPair keyPair = generateKeyPair();

        // Create a certificate builder
        X509v3CertificateBuilder certBuilder = new X509v3CertificateBuilder(
                new X500Name("CN=MySelfSignedCert"), // issuer
                BigInteger.valueOf(new SecureRandom().nextInt()), // serial number
                new Date(System.currentTimeMillis()), // start date
                new Date(System.currentTimeMillis() + 365L * 24L * 60L * 60L * 1000L), // end date
                new X500Name("CN=MySelfSignedCert"), // subject
                SubjectPublicKeyInfo.getInstance(keyPair.getPublic().getEncoded()) // public key
        );

        // Add the Extended Key Usage extension
        JcaX509ExtensionUtils extUtils = new JcaX509ExtensionUtils();
        ExtendedKeyUsage eku = new ExtendedKeyUsage(createEKU(purposes));
        certBuilder.addExtension(Extension.extendedKeyUsage, isCritical, eku);

        // Sign the certificate using the private key
        ContentSigner signer = new JcaContentSignerBuilder("SHA256withRSA").build(keyPair.getPrivate());
        X509CertificateHolder certHolder = certBuilder.build(signer);

        // Convert the certificate holder to an X509Certificate
        X509Certificate cert = new JcaX509CertificateConverter().getCertificate(certHolder);

        // Return the self-signed certificate
        return cert;
    }

    // Create a list of KeyPurposeIds from an array of purposes
    private static KeyPurposeId[] createEKU(KeyPurposeId... purposes) {
        List<KeyPurposeId> purposesList = new ArrayList<>();
        for (KeyPurposeId purpose : purposes) {
            purposesList.add(purpose);
        }
        return purposesList.toArray(new KeyPurposeId[purposesList.size()]);
    }

    private static KeyPair generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA", "BC");
            SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
            keyGen.initialize(2048, random);
            return keyGen.generateKeyPair();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        }
        return null;
    }
}
