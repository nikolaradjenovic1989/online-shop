package com.onlineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineshop.dto.ArtikalDTO;
import com.onlineshop.dto.KategorijaDTO;
import com.onlineshop.model.Artikal;
import com.onlineshop.model.Kategorija;
import com.onlineshop.service.ArtikalService;
import com.onlineshop.service.KategorijaService;
import com.onlineshop.support.ArtikalToArtikalDTO;
import com.onlineshop.support.KategorijaToKategorijaDTO;

@Controller
@RequestMapping(value="/api/kategorije")
public class KategorijaController {
	
	@Autowired
	private KategorijaService kategorijaService;
	
	@Autowired
	private KategorijaToKategorijaDTO toDTO;
	
	@Autowired
	private ArtikalService artikalService;
	
	@Autowired
	private ArtikalToArtikalDTO toArtikalDTO;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<KategorijaDTO>> getKategorije() {
		List<Kategorija> kategorije = kategorijaService.findAll();
		
		if(kategorije == null || kategorije.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(toDTO.convert(kategorije), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/{id}/artikli")
	public ResponseEntity<List<ArtikalDTO>> getArtikliByKategorijaId(@PathVariable Integer id) {
		List<Artikal> artikli = artikalService.findByKategorijaId(id);
		
		if(artikli == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<ArtikalDTO>>(toArtikalDTO.convert(artikli), HttpStatus.OK);
	}
	
	// exception handler
	@ExceptionHandler(value=DataIntegrityViolationException.class)
	public ResponseEntity<Void> handle() {
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	
}
