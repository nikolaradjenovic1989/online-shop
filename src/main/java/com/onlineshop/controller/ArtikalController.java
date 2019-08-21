package com.onlineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.onlineshop.dto.ArtikalDTO;
import com.onlineshop.model.Artikal;
import com.onlineshop.service.ArtikalService;
import com.onlineshop.support.ArtikalToArtikalDTO;

@RestController
@RequestMapping(value="/api/artikli")
public class ArtikalController {

	@Autowired
	private ArtikalService artikalService;
	
	@Autowired
	private ArtikalToArtikalDTO toDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<ArtikalDTO>> getArtikli(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) String opis,
			@RequestParam(required=false) Double cenaOd,
			@RequestParam(required=false) Double cenaDo) {
		
		List<Artikal> artikli;
		
		if(naziv != null || opis != null || cenaOd != null || cenaDo !=null) {
			artikli = artikalService.search(naziv, opis, cenaOd, cenaDo);
		}
		else {
			artikli = artikalService.findAll();
		}
		
		return new ResponseEntity<>(toDTO.convert(artikli), HttpStatus.OK);
	}
	
	@RequestMapping(value="/rastuce", method=RequestMethod.GET)
	public ResponseEntity<List<ArtikalDTO>> getArtikliRastuce() {
		
		List<Artikal> artikli = artikalService.sortByCenaRastuce();
		
		return new ResponseEntity<>(toDTO.convert(artikli), HttpStatus.OK);
	}
	
	@RequestMapping(value="/opadajuce", method=RequestMethod.GET)
	public ResponseEntity<List<ArtikalDTO>> getArtikliOpadajuce() {
		
		List<Artikal> artikli = artikalService.sortByCenaOpadajuce();
		
		return new ResponseEntity<>(toDTO.convert(artikli), HttpStatus.OK);
	}
	
	@RequestMapping(value="/popust", method=RequestMethod.GET)
	public ResponseEntity<List<ArtikalDTO>> getArtikliNaPopustu() {
		
		List<Artikal> artikli = artikalService.naPopustu();
		
		return new ResponseEntity<>(toDTO.convert(artikli), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<ArtikalDTO> getArtikal(@PathVariable Integer id){
		Artikal artikal = artikalService.findOne(id);
		
		if(artikal==null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDTO.convert(artikal), HttpStatus.OK);
	}
	
	// exception handler
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
