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
	 * ����������֤��
	 * @param email		Ŀ������
	 */
	@PostMapping("sendEmail") // ǰ�治Ҫ��б��/������404
	@ResponseBody 
	public ResultModel<Void> sendEmailCaptcha(String email, HttpSession session) {
		// �ж������Ƿ���ϸ�ʽ
		if (!TextUtils.isEmail(email)) {
			return ResultModel.error(StatusCode.PARAM_IS_INVALID);
		}
		
		// ������һ�η�����֤���Ƿ񳬹�60��
		LocalDateTime preEmailSendTime = (LocalDateTime) session.getAttribute("preEmailSendTime");
		if (!ObjectUtils.isEmpty(preEmailSendTime) &&
				ChronoUnit.SECONDS.between(preEmailSendTime, LocalDateTime.now()) <= 60) {
			return ResultModel.error(StatusCode.EMAIL_SEND_FREQUENT);
		}
		
		// ����
		String captcha = userService.sendEmailCaptcha(email);
		
		// ��������֤��浽session�У�ע��Ҫ��ͼ����֤������
		session.setAttribute("emailCaptcha", captcha);
		// �洢���û���һ�η���������֤���ʱ��
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
			model.addAttribute("message","�û����������");
			return "login";
		}
		
	}
	
	@RequestMapping(value="/user/checkUser",method=RequestMethod.POST)
	public void check(HttpServletRequest request,HttpServletResponse response)throws IOException {
		String username=request.getParameter("username");
		if(userService.checkUsername(username)) {
			response.getWriter().print("�û���"+username+"�ѱ�ע�ᣡ");
		}else
		{
			response.getWriter().print("��ϲ��"+username+"����ʹ�ã�");
		}
			
		
	}
	
	@RequestMapping("/user/regist")
	public String regist(User user,String valistr,HttpSession session,Model model) {
		if (user.getUsername()==null||user.getUsername()=="") {
			model.addAttribute("msg","�û�������Ϊ�գ�");
			return "regist";
		}
		if (user.getPassword()==null||user.getPassword()=="") {
			model.addAttribute("msg","���벻��Ϊ�գ�");
			return "regist";
		}
		String emailCaptcha = (String) session.getAttribute("emailCaptcha");
		if (!valistr.equalsIgnoreCase(emailCaptcha)) {
			model.addAttribute("msg","��֤�����");
			return "regist";
		}
		if (userService.regist(user)>0) {
			// �Ƴ�session�е�emailCaptcha��preEmailSendTime
			session.removeAttribute("emailCaptcha");
			session.removeAttribute("preEmailSendTime");
			model.addAttribute("msg","ע��ɹ���");
			return "login";
		}else {
			model.addAttribute("msg","ע��ʧ��");
			return "regist";
		}
	}

	
}
 