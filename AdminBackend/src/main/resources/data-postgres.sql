INSERT into korisnik(user_id,email,ime,lozinka, prezime, potvrdjen,odobren_od_admina)values (1,'1','1','swPQNMuG2bPB+gomyKJuuAnliiuoDx6k+5vTjGx9lrl2mr6UFFeXIZCwPWJYBnVMo3EE1xUYBXNg/kxRt81fV6k=n4SHMZCgPEL1yLSz/Qm0J7a3e7kkD6/muGiLE0Fckg==','1', true,true)
INSERT into korisnik(user_id,email,ime,lozinka, prezime, potvrdjen,odobren_od_admina)values (2,'2','1','swPQNMuG2bPB+gomyKJuuAnliiuoDx6k+5vTjGx9lrl2mr6UFFeXIZCwPWJYBnVMo3EE1xUYBXNg/kxRt81fV6k=n4SHMZCgPEL1yLSz/Qm0J7a3e7kkD6/muGiLE0Fckg==','1', true,true)
INSERT into korisnik(user_id,email,ime,lozinka, prezime, potvrdjen,odobren_od_admina)values (3,'3','1','swPQNMuG2bPB+gomyKJuuAnliiuoDx6k+5vTjGx9lrl2mr6UFFeXIZCwPWJYBnVMo3EE1xUYBXNg/kxRt81fV6k=n4SHMZCgPEL1yLSz/Qm0J7a3e7kkD6/muGiLE0Fckg==','1', true,true)

insert into uloga(id,name)values(1,'ROLE_ADMIN')
insert into uloga(id,name)values(2,'ROLE_SUPERADMIN')
insert into uloga(id,name)values(3,'ROLE_MUSTERIJA')

insert into korisnik_uloge(korisnik_list_user_id,uloge_id)values(1,1)
insert into korisnik_uloge(korisnik_list_user_id,uloge_id)values(2,2)
insert into korisnik_uloge(korisnik_list_user_id,uloge_id)values(3,3)

insert into privilegije (id,name) values (1,'LOGOUT')
insert into privilegije (id,name) values (2,'RAD_SA_MUSTERIJOM')
insert into privilegije (id,name) values (3,'RAD_SA_ADMINOM')
insert into privilegije (id,name) values (4,'OPERACIJE_SERTIFIKATA_ADMIN')
insert into privilegije (id,name) values (5,'OPERACIJE_SERTIFIKATA_MUSTERIJA')
insert into privilegije (id,name) values (6,'OPERACIJE_ZAHTEVA_ZA_SERTIFIKAT_MUSTERIJA')

/*Admin*/
insert into uloga_privilegije (role_id, privilege_id) values (1,1)
insert into uloga_privilegije (role_id, privilege_id) values (1,2)
insert into uloga_privilegije (role_id, privilege_id) values (1,4)

/*Super Admin*/
insert into uloga_privilegije (role_id, privilege_id) values (2,1)
insert into uloga_privilegije (role_id, privilege_id) values (2,3)

/*Musterija*/
insert into uloga_privilegije (role_id, privilege_id) values (3,1)
insert into uloga_privilegije (role_id, privilege_id) values (3,5)
insert into uloga_privilegije (role_id, privilege_id) values (3,6)