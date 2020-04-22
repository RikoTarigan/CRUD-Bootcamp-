package com.grit.template.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grit.template.models.UserAcces;

@Repository
public interface UserAccesRepository extends JpaRepository<UserAcces, String> {
	@Query(value="select user_acces_desc from user_acces ua "
			+ "inner join user_role ur on ua.user_role_id = ur.user_role_desc " + 
			"inner join users u on ur.user_name = u.user_name where u.user_name=:userAcces",nativeQuery = true)
	
	public List<Object[]> getMenuAccesByUser(@Param("userAcces") String useracces); 
	//public List<Object[]> getDataByQueary(@Param("productCode") String productCode,
}
