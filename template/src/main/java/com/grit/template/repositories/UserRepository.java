package com.grit.template.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.grit.template.models.User;

public interface UserRepository extends JpaRepository<User, String>{

}
