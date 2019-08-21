package com.onlineshop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.DostavljacDTO;
import com.onlineshop.model.Dostavljac;
import com.onlineshop.service.DostavljacService;

@Component
public class DostavljacDTOToDostavljac implements Converter<DostavljacDTO, Dostavljac> {

	@Autowired
	private DostavljacService dostavljacService;
	
	@Override
	public Dostavljac convert(DostavljacDTO dto) {
		if(dto == null) {
			return null;
		}
		
		Dostavljac dostavljac;
		
		if(dto.getId() != null) {
			dostavljac = dostavljacService.findOne(dto.getId());
		}
		else {
			dostavljac = new Dostavljac();
		}
		
		dostavljac.setId(dto.getId());
		dostavljac.setAdresa(dto.getAdresa());
		dostavljac.setEmail(dto.getEmail());
		dostavljac.setIme(dto.getIme());
		dostavljac.setKorisnickoIme(dto.getKorisnickoIme());
		dostavljac.setPrezime(dto.getPrezime());
		dostavljac.setTelefon(dto.getTelefon());
		dostavljac.setUloga(dto.getUloga());
		dostavljac.setLozinka(dto.getLozinka());
		
		return dostavljac;
	}

}
