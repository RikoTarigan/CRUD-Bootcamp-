package com.grit.template.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grit.template.models.Product;
import com.grit.template.models.UserRole;
import com.grit.template.repositories.UserRoleRepository;

@Service
public class UserRoleService {
	@Autowired
	private UserRoleRepository userrolerepo;
	
	public void saveUserRole(UserRole userrole)
	{
		userrolerepo.save(userrole);
	}
	public void delUserRole(UserRole userrole)
	{
		userrolerepo.delete(userrole);
	}
}
