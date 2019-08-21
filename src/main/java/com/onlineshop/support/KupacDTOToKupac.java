package com.onlineshop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.KupacDTO;
import com.onlineshop.model.Kupac;
import com.onlineshop.service.KupacService;

@Component
public class KupacDTOToKupac implements Converter<KupacDTO, Kupac> {

	@Autowired
	private KupacService kupacService;
	
	@Override
	public Kupac convert(KupacDTO dto) {
		if(dto == null) {
			return null;
		}
		
		Kupac kupac;
		
		if(dto.getId() != null) {
			kupac = kupacService.findById(dto.getId());
		}
		else {
			kupac = new Kupac();
		}
		
		kupac.setId(dto.getId());
		kupac.setAdresa(dto.getAdresa());
		kupac.setEmail(dto.getEmail());
		kupac.setIme(dto.getIme());
		kupac.setKorisnickoIme(dto.getKorisnickoIme());
		kupac.setLozinka(dto.getLozinka());
		kupac.setPrezime(dto.getPrezime());
		kupac.setTelefon(dto.getTelefon());
		kupac.setUloga(dto.getUloga());
		
		return kupac;
	}

}
