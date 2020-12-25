package easymall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("indexController")
public class IndexController  {
	
	
	
	/**
	 * jsp应该统一放置在WEB-INF/jsp下面，这样用户无法通过url直接访问jsp，
	 * 而必须统一经过我们的接口路径来访问
	 * 
	 * @return
	 */
	@GetMapping({"/", "/index"})
	public String index() {
		return "index";
	}

	@RequestMapping("/index/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/index/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index/index";
	}
	
	@RequestMapping("/index/regist")
	public String register() {
		return "regist";
	}
	
}
