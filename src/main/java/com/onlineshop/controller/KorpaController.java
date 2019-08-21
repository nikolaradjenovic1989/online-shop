package com.onlineshop.controller;

import java.sql.Date;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.onlineshop.dto.KorpaDTO;
import com.onlineshop.model.Artikal;
import com.onlineshop.model.Korpa;
import com.onlineshop.model.Kupac;
import com.onlineshop.service.KorpaService;
import com.onlineshop.service.KupacService;
import com.onlineshop.support.KorpaDTOToKorpa;
import com.onlineshop.support.KorpaToKorpaDTO;

@Controller
@RequestMapping(value="/api/korpa")
public class KorpaController {
	
	@Autowired
	private KorpaService korpaService;
	
	@Autowired
	private KorpaToKorpaDTO toDTO;
	
	@Autowired
	private KorpaDTOToKorpa toKorpa;
	
	@Autowired
	private KupacService kupacService;

	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<KorpaDTO> kreirajKorpu(@RequestBody KorpaDTO newKorpaDTO){
		
		Calendar currenttime = Calendar.getInstance();
		newKorpaDTO.setDatumIVreme(new Date((currenttime.getTime()).getTime())); // datum i vreme kupovine (trenutno)
		Korpa savedKorpa = korpaService.save(toKorpa.convert(newKorpaDTO)); // čuvanje korpe
		
		Kupac kupac = kupacService.findById(newKorpaDTO.getKupacId());
		
		for(Artikal a : kupac.getArtikliUKorpi()) {
			a.addRanijeKupljeni(kupac); // artikli se dodaju u listu ranije kupljenih preko artikla jer je tamo definisano kaskadno brisanje i jpa odmah snima promenu
		}
		for(Artikal a : kupac.getArtikliUKorpi()) {
			a.removeUKorpiKodKupca(kupac); // artikli koji su kupljeni se izbacuju iz korpe preko artikla jer je tamo definisano kaskadno brisanje
		}

		kupacService.save(kupac); // kupac se apdejtuje u bazi i snimaju se promene

		return new ResponseEntity<>(toDTO.convert(savedKorpa), HttpStatus.CREATED);
	}
	
}
