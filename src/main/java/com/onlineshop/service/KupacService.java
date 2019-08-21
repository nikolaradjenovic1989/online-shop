package com.onlineshop.service;

import java.util.List;

import com.onlineshop.model.Kupac;

public interface KupacService {

	Kupac findById (Integer id);
	
	List<Kupac> findAll();
	
	Kupac save(Kupac kupac);
	
	Kupac findByKorisnickoIme (String korisnickoIme);
	
}
