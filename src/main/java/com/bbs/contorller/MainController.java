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
import com.bbs.vo.Authmail;


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
	//url 패턴이 'path/idCheck'일 경우
	@RequestMapping(value ="/idCheck",method = RequestMethod.GET)
	
	//반환값을 페이지에 직접출력할경우
	@ResponseBody //리턴에는 경로값이 들어가는데  출력값을 가져오려면 ResponseBody가 있어야함
	public String idCheck(String user_id) throws Exception{
		
		int result =usersService.idCheck(user_id);
		
		//String str =Integer.toString(result); //문자열로 정석적인 방법
		return result+"";
	}
	
	//url 패턴이 'path/sendAuthMail'일 경우
	@RequestMapping(value = "/sendAuthMail",method = RequestMethod.GET)
	@ResponseBody
	public String sendAuthMail(String user_mail) throws Exception{
		
		int result =usersService.setAuthnum(user_mail);
		
		return result+"";
	}
			
	//url 패턴이 'path/mailAuth'일 경우
	@RequestMapping(value= "/mailAuth",method =RequestMethod.POST)
	@ResponseBody// <-이거없이 return 하면 경로값이 넘어가지만 responseBody있으면 출력값을 가짐
	public String mailAuth(Authmail authmail) throws Exception{
		
		
		return null;
	}
	
	
	
}

