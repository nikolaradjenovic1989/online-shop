package com.onlineshop.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.KategorijaDTO;
import com.onlineshop.model.Kategorija;

@Component
public class KategorijaToKategorijaDTO implements Converter<Kategorija, KategorijaDTO> {

	@Override
	public KategorijaDTO convert(Kategorija kategorija) {
		
		if(kategorija==null){
			return null;
		}
		
		KategorijaDTO dto = new KategorijaDTO();
		
		dto.setId(kategorija.getId());
		dto.setNaziv(kategorija.getNaziv());
		
		return dto;
	}

	public List<KategorijaDTO> convert(List<Kategorija> kategorije){
		List<KategorijaDTO> retVal = new ArrayList<>();
		
		for(Kategorija k: kategorije){
			retVal.add(convert(k));
		}
		
		return retVal;
	}
}
