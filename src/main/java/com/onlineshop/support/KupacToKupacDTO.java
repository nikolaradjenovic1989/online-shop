package com.onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.KupacDTO;
import com.onlineshop.model.Kupac;

@Component
public class KupacToKupacDTO implements Converter<Kupac, KupacDTO> {

	@Override
	public KupacDTO convert(Kupac kupac) {
		
		if(kupac == null) {
			return null;
		}
		
		KupacDTO dto = new KupacDTO();
		
		dto.setId(kupac.getId());
		dto.setAdresa(kupac.getAdresa());
		dto.setEmail(kupac.getEmail());
		dto.setIme(kupac.getIme());
		dto.setKorisnickoIme(kupac.getKorisnickoIme());
		dto.setLozinka(kupac.getLozinka());
		dto.setPrezime(kupac.getPrezime());
		dto.setTelefon(kupac.getTelefon());
		dto.setUloga(kupac.getUloga());
		
		return dto;
	}
	
	public List<KupacDTO> convert(List<Kupac> kupci) {
		List<KupacDTO> retVal = new ArrayList<KupacDTO>();
		for (Kupac k : kupci) {
			retVal.add(convert(k));
		}
		
		return retVal;
	}
	
}
