package com.onlineshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineshop.dto.KorisnikDTO;
import com.onlineshop.dto.KupacDTO;
import com.onlineshop.model.Korisnik;
import com.onlineshop.model.Kupac;
import com.onlineshop.service.KorisnikService;
import com.onlineshop.service.KupacService;
import com.onlineshop.support.KorisnikToKorisnikDTO;
import com.onlineshop.support.KupacDTOToKupac;
import com.onlineshop.support.KupacToKupacDTO;

@Controller
@RequestMapping(value="/api/korisnik")
public class KorisnikController {

	@Autowired
	private KorisnikService korisnikService;
	
	@Autowired
	private KorisnikToKorisnikDTO toKorisnikDTO;
	
	@Autowired
	private KupacService kupacService;
	
	@Autowired
	private KupacToKupacDTO toKupacDTO;
	
	@Autowired
	private KupacDTOToKupac toKupac;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<KorisnikDTO> getIme(Authentication auth) {
		if(auth == null) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		auth = SecurityContextHolder.getContext().getAuthentication();
	    String korisnickoIme = auth.getName(); //get logged in username
	    
	    Korisnik korisnik = korisnikService.findByKorisnickoIme(korisnickoIme);
	    
	    
		return new ResponseEntity<>(toKorisnikDTO.convert(korisnik), HttpStatus.OK);
	}
	
	@RequestMapping(value="/registracija", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<KupacDTO> add(@Validated @RequestBody KupacDTO newKupacDTO){
		
		newKupacDTO.setUloga("KUPAC");
		newKupacDTO.setLozinka(passwordEncoder.encode(newKupacDTO.getLozinka()));
		Kupac saved = kupacService.save(toKupac.convert(newKupacDTO));

		return new ResponseEntity<>(toKupacDTO.convert(saved), HttpStatus.CREATED);
	}
	
}
