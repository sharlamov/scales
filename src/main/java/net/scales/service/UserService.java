package net.scales.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import net.scales.model.CustomUser;

public interface UserService {
	
	public void updateUserDetails(CustomUser cu) throws UsernameNotFoundException;
}
