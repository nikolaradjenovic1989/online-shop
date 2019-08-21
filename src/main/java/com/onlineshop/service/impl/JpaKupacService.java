package com.onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.model.Kupac;
import com.onlineshop.repository.KupacRepository;
import com.onlineshop.service.KupacService;

@Service
public class JpaKupacService implements KupacService {

	@Autowired
	private KupacRepository kupacRepository;
	
	@Override
	public Kupac findById(Integer id) {
		Kupac kupac = kupacRepository.getOne(id);
		
		if(kupac == null) {
			return null;
		}
		
		return kupac;
	}

	@Override
	public List<Kupac> findAll() {
		return kupacRepository.findAll();
	}

	@Override
	public Kupac save(Kupac kupac) {
		return kupacRepository.save(kupac);
	}

	@Override
	public Kupac findByKorisnickoIme(String korisnickoIme) {
		return kupacRepository.findByKorisnickoIme(korisnickoIme);
	}

}
