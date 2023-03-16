INSERT into korisnik(user_id,email,ime,lozinka, prezime)values (1,'1','1','$2a$12$oqwZp./vDTjs16Mnukeq0uLU.63g4T/bA3UNlDRBURz7uQz7rAfc6','1')
insert into uloga(id,naziv)values(1,'ROLE_ADMIN')
insert into korisnik_uloge(korisnik_list_user_id,uloge_id)values(1,1)