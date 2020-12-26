package easymall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
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
		return "redirect:/index";
	}
	
	@RequestMapping("/index/regist")
	public String register() {
		return "regist";
	}
	
}
