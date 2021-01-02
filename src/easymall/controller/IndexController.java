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
	 * jspӦ��ͳһ������WEB-INF/jsp���棬�����û��޷�ͨ��urlֱ�ӷ���jsp��
	 * ������ͳһ�������ǵĽӿ�·��������
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
