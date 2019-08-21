package com.onlineshop.service;

import java.util.List;

import org.springframework.data.repository.query.Param;

import com.onlineshop.model.Artikal;

public interface ArtikalService {

	Artikal findOne(Integer id);
	
	List<Artikal> findAll();
	
	Artikal save(Artikal artikal);
	
	Artikal delete(Integer id);
	
	List<Artikal> findByKategorijaId(Integer id);
	
	List<Artikal> sortByCenaRastuce();
	
	List<Artikal> sortByCenaOpadajuce();
	
	List<Artikal> search(
			@Param("naziv") String naziv, 
			@Param("opis") String opis, 
			@Param("cenaOd") Double cenaOd,
			@Param("cenaDo") Double cenaDo);
	
	List<Artikal> omiljeniArtikliKupca (Integer kupacId);
	
	List<Artikal> artikliUKorpiKupca (Integer kupacId);
	
	List<Artikal> naPopustu();
	
	List<Artikal> ranijeKupljeniZaKupca (Integer kupacId);
	
}
