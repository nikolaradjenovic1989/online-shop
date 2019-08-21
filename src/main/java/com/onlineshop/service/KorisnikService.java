package com.onlineshop.service;

import java.util.List;

import com.onlineshop.model.Korisnik;

public interface KorisnikService {
	
	Korisnik findOneById (Integer id);
	
	List<Korisnik> findAll();
	
	Korisnik findByKorisnickoIme(String korisnickoIme);
	
	Korisnik delete (Integer id);
	
	Korisnik save (Korisnik korisnik);

}
