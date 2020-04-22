package com.grit.template.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grit.template.models.UserRole;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, String> {
	public List<UserRole> findByUserName(String username);
}
