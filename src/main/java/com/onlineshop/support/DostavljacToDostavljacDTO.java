package com.onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.DostavljacDTO;
import com.onlineshop.model.Dostavljac;

@Component
public class DostavljacToDostavljacDTO implements Converter<Dostavljac, DostavljacDTO> {

	@Override
	public DostavljacDTO convert(Dostavljac dostavljac) {
		if(dostavljac == null) {
			return null;
		}
		
		DostavljacDTO dto = new DostavljacDTO();
		
		dto.setId(dostavljac.getId());
		dto.setAdresa(dostavljac.getAdresa());
		dto.setEmail(dostavljac.getEmail());
		dto.setIme(dostavljac.getIme());
		dto.setKorisnickoIme(dostavljac.getKorisnickoIme());
		dto.setPrezime(dostavljac.getPrezime());
		dto.setTelefon(dostavljac.getTelefon());
		dto.setUloga(dostavljac.getUloga());
		
		return dto;
	}
	
	public List<DostavljacDTO> convert(List<Dostavljac> dostavljaci) {
		List<DostavljacDTO> retVal = new ArrayList<>();
		for (Dostavljac d : dostavljaci) {
			retVal.add(convert(d));
		}
		
		return retVal;
	}

}
