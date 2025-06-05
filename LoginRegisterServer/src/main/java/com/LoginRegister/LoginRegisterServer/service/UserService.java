package com.LoginRegister.LoginRegisterServer.service;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.LoginRegister.LoginRegisterServer.entity.Users;
import com.LoginRegister.LoginRegisterServer.repository.UsersRepo;
import com.LoginRegister.LoginRegisterServer.requests.LoginRequest;

@Service 

public class UserService {
	
    private final UsersRepo usersRepo;
	
	public UserService(UsersRepo usersRepo) {
		super();
		this.usersRepo = usersRepo;
	}
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	private JWTService jwtService;
	
	

	private BCryptPasswordEncoder encoder = new  BCryptPasswordEncoder(12);

	public Users addUser(Users user)
	{
		user.setPassword(encoder.encode(user.getPassword()));
		return usersRepo.save(user);
	}
	
	public String verify(Users user) {
	   org.springframework.security.core.Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(),user.getPassword()));
		if(authentication.isAuthenticated())
		{
			return jwtService.generateToken(user.getName());
		}
		
		return "fail";
			   
	}

	
	
	
	
	public Boolean loginUser(LoginRequest loginRequest)
	{
		Optional<Users> user = usersRepo.findById(loginRequest.getUserId());
		
		if(user == null)
		{
			return false;
		}
		
		Users user1 = user.get();
		
		if(!user1.getPassword().equals(loginRequest.getPassword()))
				{
			     return false;
				}
		return true;
		
	}





}
