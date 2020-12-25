package easymall.service;

import easymall.po.User;

public interface UserService {
	
	// 调用阿里的API发送验证码
	String sendEmailCaptcha(String email);

	public User login(User user);
	
	public Boolean checkUsername(String username);
	public int regist(User user);
}
