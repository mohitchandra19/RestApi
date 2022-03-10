package com.example.demo.Controller;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.RestUser;

@RestController
@RequestMapping("/users")


public class Controllerr {
	Map<String,RestUser> allUsers = new HashMap<>();
	
	
	@GetMapping
	public Collection<RestUser> getMethod()
	{
		return allUsers.values();
	}
	@PostMapping
	public String postMethod(@RequestBody RestUser userdetails)
	{
		RestUser addValue = new RestUser();
		addValue.setUserId(userdetails.getUserId());
		addValue.setEmail(userdetails.getEmail());
		addValue.setName(userdetails.getName());
		allUsers.put(userdetails.getUserId(), addValue);
		return "User Added";
	}
	@PutMapping(path="/{userId}")
	public String putMethod(@PathVariable String userId,@RequestBody RestUser userdetails)
	{
	if(allUsers.containsKey(userId))
	{
		RestUser addValue = new RestUser();
		addValue.setUserId(userdetails.getUserId());
		addValue.setEmail(userdetails.getEmail());
		addValue.setName(userdetails.getName());
		allUsers.put(userId, addValue);
		return "Edited user details";
	}
	else
	{
		return "UserId is not found";
	}
	}
	@DeleteMapping(path="/{userId}")
	public String deleteMethod(@PathVariable String userId)
	{
		if(allUsers.containsKey(userId)) 
		{
			allUsers.remove(userId);
			return "User deleted from Data";
		}
		else
		{
			return "UserId is not found";
		}
		
	}

}
