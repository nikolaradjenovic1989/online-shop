package com.onlineshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.onlineshop.model.Artikal;

@Repository
public interface ArtikalRepository extends JpaRepository<Artikal, Integer> {

	List<Artikal> findByKategorijaId (Integer id);
	
	@Query("SELECT a FROM Artikal a ORDER BY a.cena ASC")
	List<Artikal> rastuce();
	
	@Query("SELECT a FROM Artikal a ORDER BY a.cena DESC")
	List<Artikal> opadajuce();
	
	// u query ide naziv klase u ovom slučaju Artikal, a a.** mora biti naziv polja u klasi
	// query je case sensitive pa se zato sve spušta na mala slova
	@Query("SELECT a FROM Artikal a WHERE "
			+ "(LOWER(:naziv) IS NULL OR LOWER(a.naziv) like LOWER(:naziv)) AND "
			+ "(LOWER(:opis) IS NULL OR LOWER(a.opis) like LOWER(:opis)) AND "
			+ "(:cenaOd IS NULL OR a.cena >= :cenaOd) AND "
			+ "(:cenaDo IS NULL OR a.cena <= :cenaDo) ")
	List<Artikal> search(
			@Param("naziv") String naziv, 
			@Param("opis") String opis, 
			@Param("cenaOd") Double cenaOd,
			@Param("cenaDo") Double cenaDo);
	
	List<Artikal> findByKupciVoleArtikal_Id (Integer kupacId); // u nazivu pretrage je ime liste iz klase i id posto snima id u bazu (pogledati izgled baze u konzoli)
	
	List<Artikal> findByUKorpiKodKupca_Id (Integer kupacId);
	
	List<Artikal> findByPopustNotNull();
	
	List<Artikal> findByRanijeKupljeni_Id (Integer kupacId);
	
	/*
		Pročitaj malo o automatski generisanim query-jima u sprignu po imenu
		metode koje su napisane poštujući konvenciju automatski ti nađu šta ti treba
	*/
}
