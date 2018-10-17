package telran.forum.service;

import telran.forum.dto.UserProfileDto;
import telran.forum.dto.UserRegisterDto;

public interface AccountService {
	
	public UserProfileDto addUser(UserRegisterDto userRegDto, String auth);

}
