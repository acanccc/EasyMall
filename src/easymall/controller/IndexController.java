package easymall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	@GetMapping({"/", "/index", "/index/index"})
	public String index(Model model) {
		
		List<String> arr = new ArrayList<>();
		arr.add("test");
		arr.add("ppust");
		model.addAttribute("arr", arr.toString());
		return "index";
	}

	@RequestMapping("/index/login")
	public String login() {
		return "login";
	}
	@RequestMapping("/index/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@RequestMapping("/index/regist")
	public String register() {
		return "regist";
	}
	
}
