package com.onlineshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineshop.dto.KorpaDTO;
import com.onlineshop.model.Korpa;
import com.onlineshop.model.Status;
import com.onlineshop.service.KorpaService;
import com.onlineshop.support.KorpaDTOToKorpa;
import com.onlineshop.support.KorpaToKorpaDTO;

@Controller
@RequestMapping(value="/api/dostavljac")
public class DostavljacController {

	@Autowired
	private KorpaService korpaService;
	
	@Autowired
	private KorpaToKorpaDTO toDTO;
	
	@Autowired
	private KorpaDTOToKorpa toKorpa;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<KorpaDTO>> getKorpeZaDostavu() {

		List<Korpa> korpe = korpaService.findByStatus(Status.KUPLJENO);
		
		return new ResponseEntity<>(toDTO.convert(korpe), HttpStatus.OK);
	}
	
	@RequestMapping(value="/preuzete", method=RequestMethod.GET)
	public ResponseEntity<List<KorpaDTO>> getPreuzeteKorpe() {

		List<Korpa> korpe = korpaService.findByStatus(Status.DOSTAVA_U_TOKU);
		
		return new ResponseEntity<>(toDTO.convert(korpe), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{dostavljacId}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<KorpaDTO> preuzimanjePorudzbine(@RequestBody KorpaDTO korpaDTO, @PathVariable Integer dostavljacId){
		
		korpaDTO.setDostavljacId(dostavljacId);
		korpaDTO.setStatus("DOSTAVA_U_TOKU");
		
		Korpa savedKorpa = korpaService.save(toKorpa.convert(korpaDTO)); // čuvanje izmene, toKorpa klasa vodi racuna o svemu

		return new ResponseEntity<>(toDTO.convert(savedKorpa), HttpStatus.OK);
	}
	
	@RequestMapping(value="/dostavljeno", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<KorpaDTO> dostaviPorudzbinu(@RequestBody KorpaDTO korpaDTO){
		
		korpaDTO.setStatus("DOSTAVLJENO");
		Korpa savedKorpa = korpaService.save(toKorpa.convert(korpaDTO)); // čuvanje izmene, toKorpa klasa vodi racuna o svemu

		return new ResponseEntity<>(toDTO.convert(savedKorpa), HttpStatus.OK);
	}
}
