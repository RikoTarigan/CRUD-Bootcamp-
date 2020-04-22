package com.grit.template.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grit.template.models.User;
import com.grit.template.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userrepo;
	
	public void saveUser(User user)
	{
		userrepo.save(user);
	}
	public void delUser(User user)
	{
		userrepo.delete(user);
	}
}
