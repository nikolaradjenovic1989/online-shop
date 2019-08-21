package com.onlineshop.service;

import com.onlineshop.model.Admin;

public interface AdminService {

	Admin findOneById (Integer id);
	
	Admin save (Admin admin);
	
}
