package com.onlineshop.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineshop.model.Korpa;
import com.onlineshop.model.Status;

@Repository
public interface KorpaRepository extends JpaRepository<Korpa, Integer> {

	List<Korpa> findByStatus (Status status);
	
	List<Korpa> findByDatumIVreme (Date datum);
	
	List<Korpa> findByDatumIVremeBetween (Date pocetniDatum, Date krajnjiDatum);
}
