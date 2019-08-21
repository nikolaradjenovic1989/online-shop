package com.onlineshop.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("3")
public class Admin extends Korisnik {

	private static final long serialVersionUID = 1L;
	
}
