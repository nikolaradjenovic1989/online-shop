package com.onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.model.Korisnik;
import com.onlineshop.repository.KorisnikRepository;
import com.onlineshop.service.KorisnikService;

@Service
public class JpaKorisnikService implements KorisnikService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Override
	public Korisnik findOneById(Integer id) {
		if(id == null) {
			return null;
		}
		
		Korisnik korisnik = korisnikRepository.getOne(id);
		
		if(korisnik == null) {
			return null;
		}
		
		return korisnik;
	}

	@Override
	public Korisnik delete(Integer id) {
		if(id == null) {
			return null;
		}
		
		Korisnik korisnik = korisnikRepository.getOne(id);
		
		if (korisnik != null) {
			korisnikRepository.delete(korisnik);
		}
		return korisnik;
	}

	@Override
	public List<Korisnik> findAll() {
		return korisnikRepository.findAll();
	}

	@Override
	public Korisnik findByKorisnickoIme(String korisnickoIme) {
		return korisnikRepository.findByKorisnickoIme(korisnickoIme);
	}

	@Override
	public Korisnik save(Korisnik korisnik) {
		return korisnikRepository.save(korisnik);
	}

}
