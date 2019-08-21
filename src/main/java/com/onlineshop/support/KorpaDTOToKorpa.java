package com.onlineshop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.KorpaDTO;
import com.onlineshop.model.Artikal;
import com.onlineshop.model.Korpa;
import com.onlineshop.model.Status;
import com.onlineshop.service.ArtikalService;
import com.onlineshop.service.DostavljacService;
import com.onlineshop.service.KorpaService;
import com.onlineshop.service.KupacService;

@Component
public class KorpaDTOToKorpa implements Converter<KorpaDTO, Korpa> {

	@Autowired
	private KorpaService korpaService;
	
	@Autowired
	private DostavljacService dostavljacService;
	
	@Autowired
	private KupacService kupacService;
	
	@Autowired
	private ArtikalService artikalService;
	
	@Override
	public Korpa convert(KorpaDTO dto) {
		Korpa korpa;
		
		// check if we want to save or update
		if(dto.getId() != null) {
			korpa = korpaService.findOneById(dto.getId());
		} else {
			korpa = new Korpa();
		}
		
		korpa.setDatumIVreme(dto.getDatumIVreme());
		if(dto.getDostavljacId() != null) {
			korpa.setDostavljac(dostavljacService.findOne(dto.getDostavljacId()));
		}
		
		korpa.setKupac(kupacService.findById(dto.getKupacId()));
		korpa.setStatus(Status.valueOf(dto.getStatus()));
		
		for(int i=0; i < dto.getArtikliUKorpiId().size(); i++) {
			Artikal artikal = artikalService.findOne(dto.getArtikliUKorpiId().get(i));
			korpa.addKupljeniArtikal(artikal);
		}
	
		return korpa;
	}

	
}
