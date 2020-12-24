package easymall.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.Admin;
import easymall.po.User;
import easymall.service.AdminService;
import easymall.service.UserService;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping("/login")
	public String login() {
		return "adminlogin";
	}
	
	@Autowired
	private AdminService adminService;
	@RequestMapping("/dologin")
	public String dologin(Admin admin,HttpSession session,Model model) {
		Admin madmin=adminService.login(admin);
		if(madmin!=null) {
			session.setAttribute("admin", madmin);
			return "adminindex";
		}else {
			model.addAttribute("message","”√ªß√˚√‹¬Î¥ÌŒÛ£°");
			return "adminlogin";
		}
		
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index.jsp";
	}
}
