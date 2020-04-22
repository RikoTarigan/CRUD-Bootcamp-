package com.grit.template.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.grit.template.models.Transaksi;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, String>  {
	@Query(value="select u.user_name,user_first_name from users u \r\n" + 
			"inner join user_role ur on u.user_name = ur.user_name \r\n" + 
			"where ur.user_role_desc=:customer",nativeQuery = true)
	public List<Object[]> getAllCustomer(@Param("customer") String customer);
	
}
