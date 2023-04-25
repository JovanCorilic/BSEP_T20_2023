package Backend.AdminBackend.security.certificate;

import Backend.AdminBackend.model.Korisnik;
import Backend.AdminBackend.model.Sertifikat;
import Backend.AdminBackend.security.certificate.data.IssuerData;
import Backend.AdminBackend.security.certificate.data.SubjectData;
import Backend.AdminBackend.security.keystores.KeyStoreReader;
import Backend.AdminBackend.security.keystores.KeyStoreWriter;
import org.bouncycastle.asn1.x500.X500NameBuilder;
import org.bouncycastle.asn1.x500.style.BCStyle;


import java.io.File;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PravljenjeSertifikata {

    private static final String SIFRA_KEY_STORE= "PwWqaqU6tk93OvfwfCY4cQO8V+EKdYHzhuptiLhG99jz+UCPYkygcAicXWxp0cpOXXV0NMAzroqidrjetkZ0Uwk=6YWESfA2yhssxaEeS6MFbM/6mZ7oRWrBmsLwmYmSgQ==";

    public static PublicKey pravljenje(Sertifikat sertifikat){
        try {
            SubjectData subjectData = generateSubjectData(sertifikat);

            KeyPair keyPairIssuer = generateKeyPair();
            assert keyPairIssuer != null;
            IssuerData issuerData = generateIssuerData(keyPairIssuer.getPrivate(), sertifikat.getAdmin());

            CertificateGenerator cg = new CertificateGenerator();
            X509Certificate cert = cg.generateCertificate(subjectData, issuerData, sertifikat.getEkstenzije(),keyPairIssuer);
            

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

            KeyStoreReader reader = new KeyStoreReader();
            KeyStoreWriter writer = new KeyStoreWriter();

            File f = new File("src/main/resources/jks/sertifikati.cer");
            if(f.exists() && !f.isDirectory()) {
                writer.loadKeyStore("src/main/resources/jks/sertifikati.cer",SIFRA_KEY_STORE.toCharArray());
                writer.write(sertifikat.getAlias(), keyPairIssuer.getPrivate(), SIFRA_KEY_STORE.toCharArray(),cert);
                writer.saveKeyStore("src/main/resources/jks/sertifikati.cer",SIFRA_KEY_STORE.toCharArray());
            }else {
                writer.loadKeyStore(null,SIFRA_KEY_STORE.toCharArray());
                writer.write(sertifikat.getAlias(), keyPairIssuer.getPrivate(), SIFRA_KEY_STORE.toCharArray(),cert);
                writer.saveKeyStore("src/main/resources/jks/sertifikati.cer",SIFRA_KEY_STORE.toCharArray());
            }

            return keyPairIssuer.getPublic();

        } catch (CertificateException | NoSuchProviderException | InvalidKeyException | SignatureException |
                 NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static IssuerData generateIssuerData(PrivateKey issuerKey,Korisnik korisnik) {
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        builder.addRDN(BCStyle.CN, korisnik.getIme()+" "+korisnik.getPrezime());
        builder.addRDN(BCStyle.SURNAME, korisnik.getPrezime());
        builder.addRDN(BCStyle.GIVENNAME, korisnik.getIme());
        builder.addRDN(BCStyle.O, "UNS-FTN");
        builder.addRDN(BCStyle.OU, "Katedra za informatiku");
        builder.addRDN(BCStyle.C, "RS");
        builder.addRDN(BCStyle.E, korisnik.getEmail());

        // UID (USER ID) je ID korisnika
        builder.addRDN(BCStyle.UID, korisnik.getId()+"");
        // Kreiraju se podaci za issuer-a, sto u ovom slucaju ukljucuje:
        // - privatni kljuc koji ce se koristiti da potpise sertifikat koji se izdaje
        // - podatke o vlasniku sertifikata koji izdaje nov sertifikat
        return new IssuerData(issuerKey, builder.build());
    }

    private static SubjectData generateSubjectData(Sertifikat sertifikat) {

        KeyPair keyPairSubject = generateKeyPair();

        // Datumi od kad do kad vazi sertifikat
        Date startDate = sertifikat.getStartDate();
        Date endDate = sertifikat.getEndDate();

        // Serijski broj sertifikata
        String sn = sertifikat.getId()+"";

        // klasa X500NameBuilder pravi X500Name objekat koji predstavlja podatke o vlasniku
        X500NameBuilder builder = new X500NameBuilder(BCStyle.INSTANCE);
        switch (sertifikat.getNamena()) {
            case "Korisnik":
                builder.addRDN(BCStyle.CN, sertifikat.getZaKorisnika().getIme() + " " + sertifikat.getZaKorisnika().getPrezime());
                builder.addRDN(BCStyle.SURNAME, sertifikat.getZaKorisnika().getPrezime());
                builder.addRDN(BCStyle.GIVENNAME, sertifikat.getZaKorisnika().getIme());
                builder.addRDN(BCStyle.EmailAddress, sertifikat.getZaKorisnika().getEmail());
                builder.addRDN(BCStyle.UID, sertifikat.getZaKorisnika().getId() + "");
                break;
            case "Moja kuca aplikacija":
                builder.addRDN(BCStyle.SERIALNUMBER, sertifikat.getZaMojaKucaAplikacija().getSerijskiBroj());
                builder.addRDN(BCStyle.UID, sertifikat.getZaMojaKucaAplikacija().getId() + "");
                break;
            case "Uredjaj":
                builder.addRDN(BCStyle.SERIALNUMBER, sertifikat.getZaUredjaj().getSerijskiBroj());
                builder.addRDN(BCStyle.GIVENNAME, sertifikat.getZaUredjaj().getNaziv());
                builder.addRDN(BCStyle.DESCRIPTION, sertifikat.getZaUredjaj().getSvrha());
                builder.addRDN(BCStyle.UID, sertifikat.getZaUredjaj().getId() + "");
                break;
            default:
                builder.addRDN(BCStyle.UID, "1");
                break;
        }
        builder.addRDN(BCStyle.BUSINESS_CATEGORY, sertifikat.getNamena());
        builder.addRDN(BCStyle.O, "UNS-FTN");
        builder.addRDN(BCStyle.OU, "Katedra za informatiku");
        builder.addRDN(BCStyle.C, "RS");

        builder.addRDN(BCStyle.E, sertifikat.getSubjectEmail());

        // UID (USER ID) je ID korisnika
        //builder.addRDN(BCStyle.UID, "654321");

        // Kreiraju se podaci za sertifikat, sto ukljucuje:
        // - javni kljuc koji se vezuje za sertifikat
        // - podatke o vlasniku
        // - serijski broj sertifikata
        // - od kada do kada vazi sertifikat
        assert keyPairSubject != null;
        return new SubjectData(keyPairSubject.getPublic(), builder.build(), sn, startDate, endDate);

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
