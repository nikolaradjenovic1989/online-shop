package com.onlineshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.onlineshop.model.Korisnik;
import com.onlineshop.repository.KorisnikRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private KorisnikRepository korisnikRepository;
	
	@Override
	public UserDetails loadUserByUsername(String korisnickoIme) throws UsernameNotFoundException {
		
		Korisnik korisnik = korisnikRepository.findByKorisnickoIme(korisnickoIme);
		
		if(korisnik == null) {
			throw new UsernameNotFoundException(korisnickoIme);
		}
		
		return new UserDetailsImpl(korisnik);
	}

}
