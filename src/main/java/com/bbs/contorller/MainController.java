package com.bbs.contorller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bbs.service.UsersService;
import com.bbs.vo.Authmail;
import com.bbs.vo.Users;


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
	
		//model.addAttribute("msg","로그인페이지");//로그인페이지일때 띄우면 다른페이지에서 리다이렉트로 url이 로그인으로 넘어올때 새로운 msg가 나오지않음
		
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
		
		return usersService.checkAuthnum(authmail)+"";
	}
	//url 패턴이 'path/joinAction'일 경우
	@RequestMapping(value = "/joinAction", method =RequestMethod.POST)
	public String joinAction(Users users,String addr1, String addr2, String addr3) throws Exception{
		users.setUser_addr(addr1+" "+addr2+" " +addr3);
		usersService.joinAction(users);
		
		return "redirect:/login";
	}
	//url 패턴이 'path/loginAction'일 경우
	@RequestMapping(value = "/loginAction", method =RequestMethod.POST)
	public String loginAction(Users users,HttpSession session,RedirectAttributes ra) throws Exception{
		
		int result =usersService.loginAction(users);
		String url =null;
		
		if(result ==0) {
			session.setAttribute("user_id", users.getUser_id());
			url="redirect:/";
			
			//페이지 이동 ->localhost:8081/
			
		}
		else {
			ra.addFlashAttribute("msg","로그인정보가 일치하지 않습니다.");
			//request.setAttribute("msg","로그인정보가 일치하지 않습니다.");//redirect로 url을변경되었을때 request.setAttribute먹히지않음
			//메세지를 전달(로그인 정보가 잘못됐습니다.)
			url="redirect:/login";
			//페이지 이동 ->localhost:8081/login
		}
		
		return url;
	}
	
	@RequestMapping(value="/logout",method = RequestMethod.GET)
	
	public String logout(HttpSession session) throws Exception{
		
		session.invalidate();
		
		return "redirect:/";
	}
	
	//url 패턴이 'path/bbs' 일경우
	@RequestMapping(value = "/bbs", method =RequestMethod.GET)
	public String bbs(Model model) throws Exception {
		
		
		return "bbs/bbs";
	}
}

