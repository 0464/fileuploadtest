package gd.fintech.fileuploadtest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import gd.fintech.fileuploadtest.vo.User;

@Controller
public class UserController {
	@GetMapping(value={"/","/login"})
	public String login() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(User user) {
		final String ID = "admin";
		final String PW = "1234";
		
		if(user.getUserId().equals(ID)&&user.getUserPw().equals(PW)) {
			return "index";
		} else {
			return "login";
		}
	}
}
