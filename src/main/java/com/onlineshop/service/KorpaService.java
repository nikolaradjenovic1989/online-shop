package com.onlineshop.service;

import java.sql.Date;
import java.util.List;

import com.onlineshop.model.Korpa;
import com.onlineshop.model.Status;

public interface KorpaService {

	List<Korpa> findByStatus (Status status);
	
	Korpa save(Korpa korpa);
	
	Korpa findOneById(Integer id);
	
	List<Korpa> findByDatumIVreme (Date datum);
	
	List<Korpa> findByDatumIVremeBetween (Date pocetniDatum, Date krajnjiDatum);
}
