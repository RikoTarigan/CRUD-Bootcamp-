package com.grit.template.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grit.template.models.UserAcces;
import com.grit.template.repositories.UserAccesRepository;

@Service
public class UserAccesService {

	@Autowired
	private UserAccesRepository useraccesrepo;
	
	public void saveUserAcces(UserAcces useracces)
	{
		useraccesrepo.save(useracces);
	}
	public void delUserAcces(UserAcces useracces)
	{
		useraccesrepo.delete(useracces);
	}
	public List<LinkedHashMap<String, Object>> getMenuAcces(String user_acces)
	{
		if(user_acces!=null)
		{
			List<Object[]> lstDatas= useraccesrepo.getMenuAccesByUser(user_acces);
		
			List<LinkedHashMap<String, Object>> data = new ArrayList<LinkedHashMap<String,Object>>();
			LinkedHashMap<String, Object> map=null;
			
			for(Object[] obj:lstDatas)
			{
				map = new LinkedHashMap<String, Object>();
				map.put("user_acces_desc", obj[0]);
				data.add(map);
			}
			return data;
		}
		else
			return null;
		
	}

}
