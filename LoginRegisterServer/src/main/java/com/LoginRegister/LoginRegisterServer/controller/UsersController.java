package com.LoginRegister.LoginRegisterServer.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.web.csrf.CsrfToken;
//import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.LoginRegister.LoginRegisterServer.entity.Users;
//import com.LoginRegister.LoginRegisterServer.requests.LoginRequest;
import com.LoginRegister.LoginRegisterServer.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

@RestController  
public class UsersController {
	
	private final UserService userService;
	public UsersController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String greet(HttpServletRequest request)
	{
		return "Welcome to Telusko" + request.getSession().getId();
	}
	
	@GetMapping("/csrf-token")
	public CsrfToken getToken(HttpServletRequest request)  
	{
		
		return (CsrfToken) request.getAttribute("_csrf");
	}
	
	private List<Users> users = new ArrayList<>();
	
	@GetMapping("/Users")
	public List<Users> getUsers() 
	{
		return users;
	}
	

	@PostMapping("/addUser")
	@CrossOrigin(origins ="http://localhost:5173") 
	public Users addUser(@RequestBody Users user)
	{
		return userService.addUser(user);
	}
	
	@PostMapping("/login")
	@CrossOrigin(origins ="http://localhost:5173")
	public String login (@RequestBody Users user)
	{
		return userService.verify(user);
	}
	
//    @PostMapping("/loginUser")
//    @CrossOrigin(origins ="http://localhost:5173")
//    public Boolean loginUser(@RequestBody LoginRequest loginRequest)
//    {
//    	return userService.loginUser(loginRequest);
//    }
	
	
	


}
