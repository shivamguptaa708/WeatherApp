package com.LoginRegister.LoginRegisterServer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.LoginRegister.LoginRegisterServer.entity.Users;

@Repository
public interface UsersRepo extends JpaRepository<Users, String >{
	
  Users findByname(String username);
}
