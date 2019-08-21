package com.onlineshop.service;

import java.util.List;

import com.onlineshop.model.Kategorija;

public interface KategorijaService {
	
	Kategorija findOne (Integer id);
	
	List<Kategorija> findAll();
}
