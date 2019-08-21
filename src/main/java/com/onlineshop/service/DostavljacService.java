package com.onlineshop.service;

import java.util.List;

import com.onlineshop.model.Dostavljac;

public interface DostavljacService {

	Dostavljac findOne (Integer id);
	
	List<Dostavljac> findAll();
	
	Dostavljac save (Dostavljac dostavljac);
	
	Dostavljac delete(Integer id);
}
