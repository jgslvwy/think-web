package user.controller;


import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import first.UserService;


@Controller
public class UserController {
	@Resource(name="userService")
	private UserService userService;
	
	@RequestMapping("/hello/test/world")
	public void sayHello(){
		System.out.println(userService.sayHello("11")+"**************************");
	}
}
