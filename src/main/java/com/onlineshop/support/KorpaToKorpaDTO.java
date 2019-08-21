package com.onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.KorpaDTO;
import com.onlineshop.model.Artikal;
import com.onlineshop.model.Korpa;

@Component
public class KorpaToKorpaDTO implements Converter<Korpa, KorpaDTO> {

	@Override
	public KorpaDTO convert(Korpa korpa) {
		if(korpa == null) {
			return null;
		}
		
		KorpaDTO dto = new KorpaDTO();
		
		dto.setId(korpa.getId());
		dto.setDatumIVreme(korpa.getDatumIVreme());
		if(korpa.getDostavljac() != null) {
			dto.setDostavljacId(korpa.getDostavljac().getId());
		}
		dto.setKupacId(korpa.getKupac().getId());
		dto.setStatus(korpa.getStatus().name());
		dto.setNazivKupca(korpa.getKupac().getIme() + " " + korpa.getKupac().getPrezime());
		dto.setAdresaKupca(korpa.getKupac().getAdresa());
		
		Double ukupnaCena = 0.0;
		for(Artikal a : korpa.getKupljeniArtikli()) {
			ukupnaCena += a.getCenaSaPopustom();
		}
		dto.setUkupnaCena(ukupnaCena);
		
		return dto;
	}

	public List<KorpaDTO> convert(List<Korpa> korpe) {
		List<KorpaDTO> retVal = new ArrayList<KorpaDTO>();
		for (Korpa k : korpe) {
			retVal.add(convert(k));
		}
		
		return retVal;
	}
}
