package easymall.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import easymall.common.ResultModel;
import easymall.common.StatusCode;
import easymall.common.TextUtils;
import easymall.po.User;
import easymall.service.UserService;

@Controller("userController")
public class UserController {
	@Autowired
	private UserService userService;
	
	/**
	 * 发送邮箱验证码
	 * @param email		目标邮箱
	 */
	@PostMapping("sendEmail") // 前面不要加斜杠/，否则404
	@ResponseBody 
	public ResultModel<Void> sendEmailCaptcha(String email, HttpSession session) {
		// 判断邮箱是否符合格式
		if (!TextUtils.isEmail(email)) {
			return ResultModel.error(StatusCode.PARAM_IS_INVALID);
		}
		
		// 距离上一次发送验证码是否超过60秒
		LocalDateTime preEmailSendTime = (LocalDateTime) session.getAttribute("preEmailSendTime");
		if (!ObjectUtils.isEmpty(preEmailSendTime) &&
				ChronoUnit.SECONDS.between(preEmailSendTime, LocalDateTime.now()) <= 60) {
			return ResultModel.error(StatusCode.EMAIL_SEND_FREQUENT);
		}
		
		// 发送
		String captcha = userService.sendEmailCaptcha(email);
		
		// 将邮箱验证码存到session中，注意要跟图形验证码区分
		session.setAttribute("emailCaptcha", captcha);
		// 存储该用户上一次发送邮箱验证码的时间
		session.setAttribute("preEmailSendTime", LocalDateTime.now());
		
		return ResultModel.success();
	}
	
	
	@RequestMapping("/user/login")
	public String login(User user,HttpSession session,Model model) {
		User muser=userService.login(user);
		if(muser!=null) {
			session.setAttribute("user", muser);
			return "redirect:/index";
		}else {
			model.addAttribute("message","用户名密码错误！");
			return "login";
		}
		
	}
	
	@RequestMapping(value="/user/checkUser",method=RequestMethod.POST)
	public void check(HttpServletRequest request,HttpServletResponse response)throws IOException {
		String username=request.getParameter("username");
		if(userService.checkUsername(username)) {
			response.getWriter().print("用户名"+username+"已被注册！");
		}else
		{
			response.getWriter().print("恭喜您"+username+"可以使用！");
		}
			
		
	}
	
	@RequestMapping("/user/regist")
	public String regist(User user,String valistr,HttpSession session,Model model) {
		if (user.getUsername()==null||user.getUsername()=="") {
			model.addAttribute("msg","用户名不能为空！");
			return "regist";
		}
		if (user.getPassword()==null||user.getPassword()=="") {
			model.addAttribute("msg","密码不能为空！");
			return "regist";
		}
		String emailCaptcha = (String) session.getAttribute("emailCaptcha");
		if (!valistr.equalsIgnoreCase(emailCaptcha)) {
			model.addAttribute("msg","验证码错误！");
			return "regist";
		}
		if (userService.regist(user)>0) {
			// 移除session中的emailCaptcha和preEmailSendTime
			session.removeAttribute("emailCaptcha");
			session.removeAttribute("preEmailSendTime");
			model.addAttribute("msg","注册成功！");
			return "login";
		}else {
			model.addAttribute("msg","注册失败");
			return "regist";
		}
	}

	
}
 