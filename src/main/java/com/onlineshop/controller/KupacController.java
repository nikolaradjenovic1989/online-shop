package com.onlineshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineshop.dto.ArtikalDTO;
import com.onlineshop.model.Artikal;
import com.onlineshop.model.Kupac;
import com.onlineshop.service.ArtikalService;
import com.onlineshop.service.KupacService;
import com.onlineshop.support.ArtikalToArtikalDTO;

@Controller
@RequestMapping(value="/api/kupac")
public class KupacController {

	@Autowired
	private ArtikalService artikalService;
	
	@Autowired
	private KupacService kupacService;
	
	@Autowired
	private ArtikalToArtikalDTO toArtikalDTO;
	
	@RequestMapping(value="/{kupacId}/korpa", method=RequestMethod.GET)
	public ResponseEntity<List<ArtikalDTO>> getArtikliUKorpi(@PathVariable Integer kupacId) {
		List<Artikal> artikli = new ArrayList<Artikal>();
		artikli = artikalService.artikliUKorpiKupca(kupacId);
		
		if(artikli == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toArtikalDTO.convert(artikli), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{kupacId}/{artikalId}", method=RequestMethod.DELETE)
	public ResponseEntity<ArtikalDTO> obrisiIzKorpe(
			@PathVariable Integer kupacId,
			@PathVariable Integer artikalId) {
		
		Kupac kupac = kupacService.findById(kupacId);
		Artikal artikal = artikalService.findOne(artikalId);

		if(kupac == null || artikal == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		artikal.getuKorpiKodKupca().remove(kupac);
		artikalService.save(artikal);
		
		return new ResponseEntity<>(toArtikalDTO.convert(artikal), HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajUKorpu", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ArtikalDTO> dodajUKorpu(Authentication auth, @RequestBody ArtikalDTO artikalDTO) {
		auth = SecurityContextHolder.getContext().getAuthentication();
	    String korisnickoIme = auth.getName(); //get logged in username
	    
	    Kupac kupac = kupacService.findByKorisnickoIme(korisnickoIme);

	    Artikal artikal = artikalService.findOne(artikalDTO.getId());
	    
	    if(artikal == null) {
	    	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	    
	    artikal.addUKorpiKodKupca(kupac);
	    artikalService.save(artikal);
		return new ResponseEntity<>(toArtikalDTO.convert(artikal), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/{kupacId}/omiljeni", method=RequestMethod.GET)
	public ResponseEntity<List<ArtikalDTO>> getOmiljeni(@PathVariable Integer kupacId) {
		
		List<Artikal> artikli = artikalService.omiljeniArtikliKupca(kupacId);
		
		if(artikli == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toArtikalDTO.convert(artikli), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{kupacId}/ranijeKupljeni", method=RequestMethod.GET)
	public ResponseEntity<List<ArtikalDTO>> getRanijeKupljeni(@PathVariable Integer kupacId) {
		
		List<Artikal> artikli = artikalService.ranijeKupljeniZaKupca(kupacId);
		
		if(artikli == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toArtikalDTO.convert(artikli), HttpStatus.OK);
	}
	
	@RequestMapping(value="/dodajUOmiljene", method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<ArtikalDTO> dodajUOmiljene(Authentication auth, @RequestBody ArtikalDTO artikalDTO) {
		auth = SecurityContextHolder.getContext().getAuthentication();
	    String korisnickoIme = auth.getName(); //get logged in username
	    
	    Kupac kupac = kupacService.findByKorisnickoIme(korisnickoIme);

	    Artikal artikal = artikalService.findOne(artikalDTO.getId());
	    
	    if(artikal == null) {
	    	return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	    }
	    
	    artikal.getKupciVoleArtikal().add(kupac);
	    artikalService.save(artikal);
	    
		return new ResponseEntity<>(toArtikalDTO.convert(artikal), HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/omiljeni/{kupacId}/{artikalId}", method=RequestMethod.DELETE)
	public ResponseEntity<ArtikalDTO> obrisiIzListeOmiljenih(
			@PathVariable Integer kupacId,
			@PathVariable Integer artikalId) {
		
		Kupac kupac = kupacService.findById(kupacId);
		Artikal artikal = artikalService.findOne(artikalId);
		
		if(kupac == null || artikal == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	    artikal.getKupciVoleArtikal().remove(kupac);
	    artikalService.save(artikal);
		
		return new ResponseEntity<>(toArtikalDTO.convert(artikal), HttpStatus.OK);
	}
	
	// exception handler
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
