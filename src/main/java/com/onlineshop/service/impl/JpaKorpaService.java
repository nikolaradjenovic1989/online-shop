package com.onlineshop.service.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.model.Korpa;
import com.onlineshop.model.Status;
import com.onlineshop.repository.KorpaRepository;
import com.onlineshop.service.KorpaService;

@Service
public class JpaKorpaService implements KorpaService {

	@Autowired
	private KorpaRepository korpaRepository;
	
	@Override
	public List<Korpa> findByStatus(Status status) {
		return korpaRepository.findByStatus(status);
	}

	@Override
	public Korpa save(Korpa korpa) {
		return korpaRepository.save(korpa);
	}

	@Override
	public Korpa findOneById(Integer id) {
		return korpaRepository.getOne(id);
	}

	@Override
	public List<Korpa> findByDatumIVreme(Date datum) {
		return korpaRepository.findByDatumIVreme(datum);
	}

	@Override
	public List<Korpa> findByDatumIVremeBetween(Date pocetniDatum, Date krajnjiDatum) {
		return korpaRepository.findByDatumIVremeBetween(pocetniDatum, krajnjiDatum);
	}

}
