package com.onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.ArtikalDTO;
import com.onlineshop.model.Artikal;

@Component
public class ArtikalToArtikalDTO implements Converter<Artikal, ArtikalDTO> {

	@Override
	public ArtikalDTO convert(Artikal artikal) {
		
		if (artikal == null) {
			return null;
		}
		
		ArtikalDTO dto = new ArtikalDTO();
		
		dto.setId(artikal.getId());
		dto.setCena(artikal.getCena());
		dto.setImageUrl(artikal.getImageUrl());
		if (artikal.getKategorija() != null) {
			dto.setKategorijaId(artikal.getKategorija().getId());			
		}
		dto.setNaziv(artikal.getNaziv());
		dto.setOpis(artikal.getOpis());
		if (artikal.getKategorija() != null) {
			dto.setNazivKategorije(artikal.getKategorija().getNaziv());		
		}
		dto.setPopust(artikal.getPopust());
		dto.setCenaSaPopustom(artikal.getCenaSaPopustom());
		
		return dto;
	}

	public List<ArtikalDTO> convert(List<Artikal> artikli) {
		List<ArtikalDTO> retVal = new ArrayList<ArtikalDTO>();
		for (Artikal a : artikli) {
			retVal.add(convert(a));
		}
		
		return retVal;
	}
	
}
