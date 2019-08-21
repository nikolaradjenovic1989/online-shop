package com.onlineshop.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.model.Dostavljac;
import com.onlineshop.repository.DostavljacRepository;
import com.onlineshop.service.DostavljacService;

@Service
public class JpaDostavljacService implements DostavljacService {

	@Autowired
	private DostavljacRepository dostavljacRepository;
	
	@Override
	public Dostavljac findOne(Integer id) {
		Dostavljac dostavljac = dostavljacRepository.getOne(id);
		
		return dostavljac;
	}

	@Override
	public List<Dostavljac> findAll() {
		return dostavljacRepository.findAll();
	}

	@Override
	public Dostavljac save(Dostavljac dostavljac) {
		return dostavljacRepository.save(dostavljac);
	}

	@Override
	public Dostavljac delete(Integer id) {
		Dostavljac dostavljac = dostavljacRepository.getOne(id);
		if(dostavljac != null) {
			dostavljacRepository.delete(dostavljac);
		}
		return dostavljac;
	}

}
