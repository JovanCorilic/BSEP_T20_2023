package Backend.AdminBackend.security.certificate;

import Backend.AdminBackend.model.Ekstenzije;
import Backend.AdminBackend.model.ekstenzije.*;
import Backend.AdminBackend.security.certificate.data.IssuerData;
import Backend.AdminBackend.security.certificate.data.SubjectData;
import org.bouncycastle.asn1.x509.*;
import org.bouncycastle.cert.CertIOException;
import org.bouncycastle.cert.X509CertificateHolder;
import org.bouncycastle.cert.X509v3CertificateBuilder;
import org.bouncycastle.cert.jcajce.JcaX509CertificateConverter;
import org.bouncycastle.cert.jcajce.JcaX509ExtensionUtils;
import org.bouncycastle.cert.jcajce.JcaX509v3CertificateBuilder;
import org.bouncycastle.operator.ContentSigner;
import org.bouncycastle.operator.OperatorCreationException;
import org.bouncycastle.operator.jcajce.JcaContentSignerBuilder;

import java.math.BigInteger;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CertificateGenerator {
    public CertificateGenerator() {
    }

    public X509Certificate generateCertificate(SubjectData subjectData, IssuerData issuerData, Ekstenzije ekstenzije, KeyPair keyPair) {
        try {
            // Posto klasa za generisanje sertifikata ne moze da primi direktno privatni kljuc pravi se builder za objekat
            // Ovaj objekat sadrzi privatni kljuc izdavaoca sertifikata i koristiti se za potpisivanje sertifikata
            // Parametar koji se prosledjuje je algoritam koji se koristi za potpisivanje sertifikata
            JcaContentSignerBuilder builder = new JcaContentSignerBuilder("SHA256WithRSAEncryption");

            // Takodje se navodi koji provider se koristi, u ovom slucaju Bouncy Castle
            builder = builder.setProvider("BC");

            // Formira se objekat koji ce sadrzati privatni kljuc i koji ce se koristiti za potpisivanje sertifikata
            ContentSigner contentSigner = builder.build(issuerData.getPrivateKey());

            // Postavljaju se podaci za generisanje sertifikata
            X509v3CertificateBuilder certGen = new JcaX509v3CertificateBuilder(
                    issuerData.getX500name(),
                    new BigInteger(subjectData.getSerialNumber()),
                    subjectData.getStartDate(),
                    subjectData.getEndDate(),
                    subjectData.getX500name(),
                    subjectData.getPublicKey());

            // Add the extension
            JcaX509ExtensionUtils extUtils = new JcaX509ExtensionUtils();
            if (ekstenzije.getAuthorityKeyIdentifierEkstenzije().isDaLiKoristi()){
                AuthorityKeyIdentifierEkstenzije authorityKeyIdentifierEkstenzije = ekstenzije.getAuthorityKeyIdentifierEkstenzije();
                AuthorityKeyIdentifier authorityKeyID = extUtils.createAuthorityKeyIdentifier(subjectData.getPublicKey());
                certGen.addExtension(Extension.authorityKeyIdentifier, authorityKeyIdentifierEkstenzije.isCritical(), authorityKeyID);
            }
            if (ekstenzije.getBasicConstraintsEkstenzije().isDaLiKoristi()){
                BasicConstraintsEkstenzije basicConstraintsEkstenzije = ekstenzije.getBasicConstraintsEkstenzije();
                BasicConstraints basicConstraints = new BasicConstraints(basicConstraintsEkstenzije.isCA());
                if (basicConstraintsEkstenzije.isCA() && basicConstraintsEkstenzije.getMaxPathLen()>=0){
                    basicConstraints = new BasicConstraints(basicConstraintsEkstenzije.getMaxPathLen());
                }
                certGen.addExtension(Extension.basicConstraints, basicConstraintsEkstenzije.isCritical(), basicConstraints);
            }
            if (ekstenzije.getExtendedKeyUsageEkstenzije().isDaLiKoristi()){
                ExtendedKeyUsageEkstenzije extendedKeyUsageEkstenzije= ekstenzije.getExtendedKeyUsageEkstenzije();
                List<KeyPurposeId> ekuList = ExtendedKeyUsageMetoda(extendedKeyUsageEkstenzije);
                if (!ekuList.isEmpty()){
                    KeyPurposeId[] ekuArray = ekuList.toArray(new KeyPurposeId[ekuList.size()]);
                    certGen.addExtension(Extension.extendedKeyUsage, extendedKeyUsageEkstenzije.isCritical(), new ExtendedKeyUsage(ekuArray));
                }
            }
            if (ekstenzije.getKeyUsageEkstenzije().isDaLiKoristi()){
                KeyUsageEkstenzije keyUsageEkstenzije = ekstenzije.getKeyUsageEkstenzije();
                int key = KeyUsageMetoda(keyUsageEkstenzije);
                if (key != -1){
                    KeyUsage keyUsage = new KeyUsage(key);
                    certGen.addExtension(Extension.keyUsage,keyUsageEkstenzije.isCritical(),keyUsage);
                }
            }
            if (ekstenzije.getSubjectAlternativeNameEkstenzije().isDaLiKoristi()){
                SubjectAlternativeNameEkstenzije subjectAlternativeNameEkstenzije = ekstenzije.getSubjectAlternativeNameEkstenzije();
                List<GeneralName>nameList = SubjectAlternativeNameMetoda(subjectAlternativeNameEkstenzije);
                if (!nameList.isEmpty()) {
                    GeneralNames sanNames = new GeneralNames(nameList.toArray(new GeneralName[nameList.size()]));
                    certGen.addExtension(Extension.subjectAlternativeName, subjectAlternativeNameEkstenzije.isCritical(), sanNames);
                }
            }
            if (ekstenzije.getSubjectKeyIdentifierEkstenzije().isDaLiKoristi()){
                SubjectKeyIdentifierEkstenzije subjectKeyIdentifierEkstenzije = ekstenzije.getSubjectKeyIdentifierEkstenzije();
                certGen.addExtension(Extension.subjectKeyIdentifier, subjectKeyIdentifierEkstenzije.isCritical(),
                        extUtils.createSubjectKeyIdentifier(subjectData.getPublicKey()));
            }
            // Generise se sertifikat
            X509CertificateHolder certHolder = certGen.build(contentSigner);

            // Builder generise sertifikat kao objekat klase X509CertificateHolder
            // Nakon toga je potrebno certHolder konvertovati u sertifikat, za sta se koristi certConverter
            JcaX509CertificateConverter certConverter = new JcaX509CertificateConverter();
            certConverter = certConverter.setProvider("BC");

            // Konvertuje objekat u sertifikat
            return certConverter.getCertificate(certHolder);
        } catch (IllegalArgumentException | IllegalStateException | OperatorCreationException | CertificateException |
                 NoSuchAlgorithmException | CertIOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int KeyUsageMetoda(KeyUsageEkstenzije keyUsageEkstenzije){
        int key = -1;
        if (keyUsageEkstenzije.isDigitalSignature())
            key = KeyUsage.digitalSignature;
        if (keyUsageEkstenzije.isNonRepudiation())
            if (key != -1)
                key = key | KeyUsage.nonRepudiation;
            else
                key = KeyUsage.nonRepudiation;
        if (keyUsageEkstenzije.isKeyEncipherment())
            if (key != -1)
                key = key | KeyUsage.keyEncipherment;
            else
                key = KeyUsage.keyEncipherment;
        if (keyUsageEkstenzije.isDataEncipherment())
            if (key != -1)
                key = key | KeyUsage.dataEncipherment;
            else
                key = KeyUsage.dataEncipherment;
        if (keyUsageEkstenzije.isKeyAgreement())
            if (key != -1)
                key = key | KeyUsage.keyAgreement;
            else
                key = KeyUsage.keyAgreement;
        if (keyUsageEkstenzije.isKeyCertSign())
            if (key != -1)
                key = key | KeyUsage.keyCertSign;
            else
                key = KeyUsage.keyCertSign;
        if (keyUsageEkstenzije.isCRLSign())
            if (key != -1)
                key = key | KeyUsage.cRLSign;
            else
                key = KeyUsage.cRLSign;
        if (keyUsageEkstenzije.isEncipherOnly())
            if (key != -1)
                key = key | KeyUsage.encipherOnly;
            else
                key = KeyUsage.encipherOnly;
        if (keyUsageEkstenzije.isDecipherOnly())
            if (key != -1)
                key = key | KeyUsage.decipherOnly;
            else
                key = KeyUsage.decipherOnly;
        return key;
    }

    public List<KeyPurposeId> ExtendedKeyUsageMetoda(ExtendedKeyUsageEkstenzije extendedKeyUsageEkstenzije){
        List<KeyPurposeId> ekuList = new ArrayList<>();
        if (extendedKeyUsageEkstenzije.isAnyExtendedKeyUsage())
            ekuList.add(KeyPurposeId.anyExtendedKeyUsage);
        if (extendedKeyUsageEkstenzije.isId_kp_codeSigning())
            ekuList.add(KeyPurposeId.id_kp_codeSigning);
        if (extendedKeyUsageEkstenzije.isId_kp_emailProtection())
            ekuList.add(KeyPurposeId.id_kp_emailProtection);
        if (extendedKeyUsageEkstenzije.isId_kp_ipsecEndSystem())
            ekuList.add(KeyPurposeId.id_kp_ipsecEndSystem);
        if (extendedKeyUsageEkstenzije.isId_kp_ipsecUser())
            ekuList.add(KeyPurposeId.id_kp_ipsecUser);
        if (extendedKeyUsageEkstenzije.isId_kp_timeStamping())
            ekuList.add(KeyPurposeId.id_kp_timeStamping);
        if (extendedKeyUsageEkstenzije.isId_kp_OCSPSigning())
            ekuList.add(KeyPurposeId.id_kp_OCSPSigning);
        if (extendedKeyUsageEkstenzije.isId_kp_smartcardlogon())
            ekuList.add(KeyPurposeId.id_kp_smartcardlogon);
        return ekuList;
    }

    public List<GeneralName> SubjectAlternativeNameMetoda(SubjectAlternativeNameEkstenzije subjectAlternativeNameEkstenzije){
        List<GeneralName>nameList = new ArrayList<>();
        for (AlternativeName name : subjectAlternativeNameEkstenzije.getAlternativeNames()){
            switch (name.getGeneralNameType()){
                case "Directory Name":
                    nameList.add(new GeneralName(GeneralName.directoryName, name.getGeneralNameContent()));
                    break;
                case "DNS Name":
                    nameList.add(new GeneralName(GeneralName.dNSName, name.getGeneralNameContent()));
                    break;
                case "IP Address":
                    nameList.add(new GeneralName(GeneralName.iPAddress, name.getGeneralNameContent()));
                    break;
                case "Registered ID":
                    nameList.add(new GeneralName(GeneralName.registeredID, name.getGeneralNameContent()));
                    break;
                case "RFC 822 Name":
                    nameList.add(new GeneralName(GeneralName.rfc822Name, name.getGeneralNameContent()));
                    break;
                case "URI":
                    nameList.add(new GeneralName(GeneralName.uniformResourceIdentifier, name.getGeneralNameContent()));
                    break;
            }
        }
        return nameList;
    }
}






//Ekstenzija

//CA
//AuthorityKeyIdentifier - Koristis public key
//BasicConstraints
//KeyUsage KeyUsage keyUsage = new KeyUsage(KeyUsage.cRLSign | KeyUsage.keyEncipherment); nesto = nesto | KeyUsage.decipherOnly;
//SubjectKeyIdentifier

//SSL Server
//AuthorityKeyIdentifier
//ExtendedKeyUsage
//KeyUsage
// nema Subject Alternatice name
//SubjectKeyIdentifier

//SSLClient
//AuthorityKeyIdentifier
//ExtendedKeyUsage
//KeyUsage
//SubjectKeyIdentifier

//Code Signing
//AuthorityKeyIdentifier
//ExtendedKeyUsage
//KeyUsage
//SubjectKeyIdentifier

//Ekstenzije
//AuthorityKeyIdentifierEkstenzije
//BasicConstraintsEkstenzije
//KeyUsageEkstenzije
//SubjectKeyIdentifierEkstenzije
//ExtendedKeyUsageEkstenzije
//SubjectAlternaticeNameEkstenzije



