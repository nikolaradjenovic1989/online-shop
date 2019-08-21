package com.onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.KorisnikDTO;
import com.onlineshop.model.Korisnik;

@Component
public class KorisnikToKorisnikDTO implements Converter<Korisnik, KorisnikDTO> {

	@Override
	public KorisnikDTO convert(Korisnik korisnik) {
		if(korisnik == null) {
			return null;
		}
		
		KorisnikDTO dto = new KorisnikDTO();
		
		dto.setId(korisnik.getId());
		dto.setTip_korisnika(korisnik.getTip_korisnika());
		dto.setAdresa(korisnik.getAdresa());
		dto.setEmail(korisnik.getEmail());
		dto.setIme(korisnik.getIme());
		dto.setKorisnickoIme(korisnik.getKorisnickoIme());
		dto.setPrezime(korisnik.getPrezime());
		dto.setTelefon(korisnik.getTelefon());
		dto.setUloga(korisnik.getUloga());
		
		return dto;
	}

	public List<KorisnikDTO> convert(List<Korisnik> korisnici) {
		List<KorisnikDTO> retVal = new ArrayList<>();
		for (Korisnik k : korisnici) {
			retVal.add(convert(k));
		}
		
		return retVal;
	}
	
}
