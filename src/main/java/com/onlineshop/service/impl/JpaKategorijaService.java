package com.onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.model.Kategorija;
import com.onlineshop.repository.KategorijaRepository;
import com.onlineshop.service.KategorijaService;

@Service
public class JpaKategorijaService implements KategorijaService {

	@Autowired
	private KategorijaRepository kategorijaRepository;
	
	@Override
	public List<Kategorija> findAll() {
		return kategorijaRepository.findAll();
	}

	@Override
	public Kategorija findOne(Integer id) {
		return kategorijaRepository.getOne(id);
	}

}
