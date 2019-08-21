package com.onlineshop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.ArtikalDTO;
import com.onlineshop.model.Artikal;
import com.onlineshop.service.ArtikalService;
import com.onlineshop.service.KategorijaService;

@Component
public class ArtikalDTOToArtikal implements Converter<ArtikalDTO, Artikal> {

	@Autowired
	private ArtikalService artikalService;
	
	@Autowired
	private KategorijaService kategorijaService;
	
	@Override
	public Artikal convert(ArtikalDTO dto) {
		Artikal artikal;
		
		if(dto.getId() != null) {
			artikal = artikalService.findOne(dto.getId());
		} else {
			artikal = new Artikal();
		}
		
		artikal.setId(dto.getId());
		artikal.setCena(dto.getCena());
		artikal.setImageUrl(dto.getImageUrl());
		artikal.setKategorija(kategorijaService.findOne(dto.getKategorijaId()));
		artikal.setNaziv(dto.getNaziv());
		artikal.setOpis(dto.getOpis());
		artikal.setPopust(dto.getPopust());
		
		return artikal;
	}

	
}
