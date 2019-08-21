package com.onlineshop.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.onlineshop.dto.AdminDTO;
import com.onlineshop.model.Admin;
import com.onlineshop.service.AdminService;

@Component
public class AdminDTOToAdmin implements Converter<AdminDTO, Admin> {

	@Autowired
	private AdminService adminService;
	
	@Override
	public Admin convert(AdminDTO dto) {
		if(dto == null) {
			return null;
		}
		
		Admin admin;
		
		if(dto.getId() != null) {
			admin = adminService.findOneById(dto.getId());
		}
		else {
			admin = new Admin();
		}
		
		admin.setId(dto.getId());
		admin.setAdresa(dto.getAdresa());
		admin.setEmail(dto.getEmail());
		admin.setIme(dto.getIme());
		admin.setKorisnickoIme(dto.getKorisnickoIme());
		admin.setPrezime(dto.getPrezime());
		admin.setTelefon(dto.getTelefon());
		admin.setUloga(dto.getUloga());
		
		return admin;
	}

}
