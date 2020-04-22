package com.grit.template.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.grit.template.models.User;
import com.grit.template.models.UserRole;
import com.grit.template.repositories.UserRepository;
import com.grit.template.repositories.UserRoleRepository;


@Service
public class CustomUserDetailService implements UserDetailsService{

	@Autowired
	private UserRepository userrepo;
	@Autowired
	private UserRoleRepository userrolerepo;
		
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User us = new User();
		
		Optional<User> user =userrepo.findById(username);
		if(user.isPresent())
		{
			us = user.get();
		}
		
		List<UserRole> lstuserrole = userrolerepo.findByUserName(username);
		
		String[] userRoles = lstuserrole.stream().map((roles) -> roles.getUserRoleDesc()).toArray(String[]::new);
		
		List<GrantedAuthority> grantedAuth = AuthorityUtils.createAuthorityList(userRoles);
		
		  return new org.springframework.security.core.userdetails.User(username,
	                us.getUserPassword(),grantedAuth);

	}
}
