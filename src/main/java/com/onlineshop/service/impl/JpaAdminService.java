package com.onlineshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.onlineshop.model.Admin;
import com.onlineshop.repository.AdminRepository;
import com.onlineshop.service.AdminService;

@Service
public class JpaAdminService implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Override
	public Admin findOneById(Integer id) {
		Admin admin = adminRepository.getOne(id);
		
		if(admin == null) {
			return null;
		}
		
		return admin;
	}

	@Override
	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}

}
