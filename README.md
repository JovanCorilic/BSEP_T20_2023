# BSEP_T20_2023
Projekat za predmet bezbednost u sistemima elektronskog poslovanje.
## Opis
Bavi se pravljenjem i radom sa sertifikatima. Koristi se HTTPS umesto HTTP. Klijentski sloj je implementiran u Angular radnom okviru. Server je razvijen kao REST aplikacija u radnom okviru Spring uz oslonac na PostgreSQL bazu. U API gateway-u implementiran je RBAC sa fingerprint cookie-jem.

# Član
Jovan Ćorilić SW48/2017

## Pokretanje
- Za frontend :
`
ng serve --ssl true --ssl-cert "client.cer" --ssl-key "client.key"
` <br>
- Za backend : Koristiti Visual Studio Code ili neki drugi developer enviroment program za pokretanje
## Opis
- Urađen je deo projekta vezan za Admin aplikaciju. Postavljen je RUBAK sistem, ima custom password encoder ( hash + salt), provera unosa podataka i na frontend i na backend. Urađeno je pravljenje sertifikata sa ekstenzijama . Templejti za ekstenzije su :  CA , SSL Server, SSL Client, Code Signing. Postavljena je i email verifikacija za registraciju i slanje zahteva za sertifikat. <br>
- Kod Rubak sistema imamo 3 uloge : Admin, Superadmin i Mušterija. Superadmin pravi admine. Admini odobravaju registracije mušterija i zahteve za sertifikat. Oni pregledaju sertifikate i mogu da ih povuku. Mušterija pravi zahteve za sertifikat.<br>
- Za frontend je korišćen Angular a za backend Spring. Baza podataka je PostgreSQL a za čuvanje sertifikata koristi se keystore.
