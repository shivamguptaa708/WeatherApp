package com.LoginRegister.LoginRegisterServer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.LoginRegister.LoginRegisterServer.entity.UserPrincipal;
import com.LoginRegister.LoginRegisterServer.entity.Users;
import com.LoginRegister.LoginRegisterServer.repository.UsersRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private UsersRepo repo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Users user = repo.findByname(username);
		
		if(user == null)
		{
			System.out.print("User Not Found");
			throw new UsernameNotFoundException("user not found");
		}
		
		return new UserPrincipal(user);
	}

}
