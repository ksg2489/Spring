package com.bbs.contorller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bbs.service.UsersService;


@Controller
public class MainController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	@Inject
	UsersService usersService;
	
	//url 패턴이 'path/'일 경우
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String main(Model model) throws Exception {
	
		
		return "main/main";
	}
	//url 패턴이 'path/join'일 경우
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join(Model model) throws Exception {
	
		model.addAttribute("msg","반갑습니다.");
		
		return "main/join";
	}
	//url 패턴이 'path/login'일 경우
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) throws Exception {
	
		
		return "main/login";
	}
	//http://localhost:8081/idCheck?user_id=qwer
	@RequestMapping(value ="/idCheck",method = RequestMethod.GET)
	@ResponseBody //리턴에는 경로값이 들어가는데  출력값을 가져오려면 ResponseBody가 있어야함
	public String idCheck(String user_id) throws Exception{
		
		int result =usersService.idCheck(user_id);
		
		//String str =Integer.toString(result); //문자열로 정석적인 방법
		return result+"";
	}
	
}

