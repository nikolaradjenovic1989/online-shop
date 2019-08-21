package com.onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.model.Artikal;
import com.onlineshop.repository.ArtikalRepository;
import com.onlineshop.service.ArtikalService;

@Service
public class JpaArtikalService implements ArtikalService {

	@Autowired
	private ArtikalRepository artikalRepository;
	
	@Override
	public Artikal findOne(Integer id) {
		return artikalRepository.getOne(id);
	}

	@Override
	public List<Artikal> findAll() {
		return artikalRepository.findAll();
	}

	@Override
	public Artikal save(Artikal artikal) {
		return artikalRepository.save(artikal);
	}

	@Override
	public Artikal delete(Integer id) {
		Artikal artikal = artikalRepository.getOne(id);
		
		if (artikal != null) {
			artikalRepository.delete(artikal);
		}
		return artikal;
	}

	@Override
	public List<Artikal> findByKategorijaId(Integer id) {
		return artikalRepository.findByKategorijaId(id);
	}

	@Override
	public List<Artikal> search(String naziv, String opis, Double cenaOd, Double cenaDo) {
		if(naziv != null) {
			naziv = '%' + naziv + '%';
		}
		if(opis != null) {
			opis = '%' + opis + '%';
		}
		return artikalRepository.search(naziv, opis, cenaOd, cenaDo);
	}

	@Override
	public List<Artikal> sortByCenaRastuce() {
		return artikalRepository.rastuce();
	}

	@Override
	public List<Artikal> sortByCenaOpadajuce() {
		return artikalRepository.opadajuce();
	}

	@Override
	public List<Artikal> omiljeniArtikliKupca(Integer kupacId) {
		return artikalRepository.findByKupciVoleArtikal_Id(kupacId);
	}

	@Override
	public List<Artikal> artikliUKorpiKupca(Integer kupacId) {
		return artikalRepository.findByUKorpiKodKupca_Id(kupacId);
	}

	@Override
	public List<Artikal> naPopustu() {
		return artikalRepository.findByPopustNotNull();
	}

	@Override
	public List<Artikal> ranijeKupljeniZaKupca(Integer kupacId) {
		return artikalRepository.findByRanijeKupljeni_Id(kupacId);
	}

}
