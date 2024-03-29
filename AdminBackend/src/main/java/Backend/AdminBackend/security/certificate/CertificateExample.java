package Backend.AdminBackend.security.certificate;

import Backend.AdminBackend.model.Ekstenzije;
import Backend.AdminBackend.security.Base64Utility;
import Backend.AdminBackend.security.keystores.KeyStoreReader;
import Backend.AdminBackend.security.keystores.KeyStoreWriter;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import Backend.AdminBackend.security.certificate.CertificateGenerator;
import Backend.AdminBackend.security.certificate.data.IssuerData;
import Backend.AdminBackend.security.certificate.data.SubjectData;

import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CertificateExample {
    public CertificateExample() {
        Security.addProvider(new BouncyCastleProvider());
    }

    public void testIt() {
        try {
            SubjectData subjectData = generateSubjectData();

            KeyPair keyPairIssuer = generateKeyPair();
            IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate());

            // Generise se sertifikat za subjekta, potpisan od strane issuer-a
            CertificateGenerator cg = new CertificateGenerator();
            X509Certificate cert = cg.generateCertificate(subjectData, issuerData, new Ekstenzije(),keyPairIssuer);

            System.out.println("\n===== Podaci o izdavacu sertifikata =====");
            System.out.println(cert.getIssuerX500Principal().getName());
            System.out.println("\n===== Podaci o vlasniku sertifikata =====");
            System.out.println(cert.getSubjectX500Principal().getName());
            System.out.println("\n===== Sertifikat =====");
            System.out.println("-------------------------------------------------------");
            System.out.println(cert);
            System.out.println("-------------------------------------------------------");

            // Moguce je proveriti da li je digitalan potpis sertifikata ispravan, upotrebom javnog kljuca izdavaoca
            cert.verify(keyPairIssuer.getPublic());
            System.out.println("\nValidacija uspesna :)");

            System.out.println(Base64Utility.encode(cert.getSubjectX500Principal().getEncoded()));


            System.out.println(cert.getSubjectDN().toString());//CN=Marija Kovacevic,SURNAME=Kovacevic,GIVENNAME=Marija,O=UNS-FTN,OU=Katedra za informatiku,C=RS,E=marija.kovacevic@uns.ac.rs,UID=654321
            System.out.println(cert.getNotAfter()); //Fri Mar 01 00:00:00 CET 2024
            System.out.println(cert.getNotBefore()); //Tue Mar 01 00:00:00 CET 2022

            KeyStoreReader reader = new KeyStoreReader();
            KeyStoreWriter writer = new KeyStoreWriter();

            //writer.loadKeyStore(null,"1".toCharArray());
            /*writer.loadKeyStore("src/main/resources/jks/test.jks","1".toCharArray());
            writer.write("Nesto3", keyPairIssuer.getPrivate(), "1".toCharArray(),cert);
            writer.saveKeyStore("src/main/resources/jks/test.jks","1".toCharArray());*/

            X509Certificate temp = (X509Certificate) reader.readCertificate("src/main/resources/jks/test.jks","1","Nesto3");
            System.out.println("Ovde "+temp.getNotAfter());

            // Ovde se desava exception, jer se validacija vrsi putem drugog kljuca
            KeyPair anotherPair = generateKeyPair();
            cert.verify(anotherPair.getPublic());
        } catch (CertificateException | InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            System.out.println("\nValidacija neuspesna :(");
            e.printStackTrace();
        }
    }

    private IssuerData generateIssuerData(PrivateKey issuerKey) {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, "Goran Sladic");
        builder.addRDN(BCStyle.SURNAME, "Sladic");
        builder.addRDN(BCStyle.GIVENNAME, "Goran");
        builder.addRDN(BCStyle.O, "UNS-FTN");
        builder.addRDN(BCStyle.OU, "Katedra za informatiku");
        builder.addRDN(BCStyle.C, "RS");
        builder.addRDN(BCStyle.E, "sladicg@uns.ac.rs");

        // UID (USER ID) je ID korisnika
        builder.addRDN(BCStyle.UID, "123456");
        // Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
        // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
        // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
        return new IssuerData(issuerKey, builder.build());
    }

    private SubjectData generateSubjectData() {
        try {
            KeyPair keyPairSubject = generateKeyPair();

            // Datumi od kad do kad vazi sertifikat
            SimpleDateFormat iso8601Formater = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = iso8601Formater.parse("2022-03-01");
            Date endDate = iso8601Formater.parse("2025-03-01");

            // Serijski broj sertifikata
            String sn = "1";

            // klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
            X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
            builder.addRDN(BCStyle.CN, "Marija Kovacevic");
            builder.addRDN(BCStyle.SURNAME, "Kovacevic");
            builder.addRDN(BCStyle.GIVENNAME, "Marija");
            builder.addRDN(BCStyle.O, "UNS-FTN");
            builder.addRDN(BCStyle.OU, "Katedra za informatiku");
            builder.addRDN(BCStyle.C, "RS");
            builder.addRDN(BCStyle.E, "marija.kovacevic@uns.ac.rs");

            // UID (USER ID) je ID korisnika
            builder.addRDN(BCStyle.UID, "654321");

            // Kreiraju se podaci za sertifikat, sto ukljucuje:
            // - javni kljuc koji se vezuje za sertifikat
            // - podatke o vlasniku
            // - serijski broj sertifikata
            // - od kada do kada vazi sertifikat
            return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private KeyPair generateKeyPair() {
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

    public static void main(String[] args) {
        CertificateExample certificateExample = new CertificateExample();
        certificateExample.testIt();
    }
}
