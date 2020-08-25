package bc.project.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import bc.project.users.User;
import bc.project.web.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);

}
