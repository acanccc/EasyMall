package easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.common.EmailHelper;
import easymall.common.RandomUtils;
import easymall.dao.UserDao;
import easymall.po.User;
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User login(User user) {
		return userDao.login(user);
	}

	@Override
	public Boolean checkUsername(String username) {
		User us=userDao.checkUsername(username);
		if(us!=null) {
			return true;
		}else {
			return false;
		}
	}

	@Override
	public int regist(User user) {
		int n=userDao.regist(user);
		return n;
	}

	@Override
	public String sendEmailCaptcha(String email) {
		// 生成6位随机验证码
		String captcha = RandomUtils.getMixedStr(6);
		// 邮件的内容
		String htmlBody = "欢迎注册EasyMall，您正在验证邮箱，邮箱验证码为：" + captcha + "！";
		// 发送邮件
		EmailHelper.singleSend(new EmailHelper.EmailBean(
				"passerbyYSQ", email, "注册EasyMall，邮箱验证", htmlBody));
		return captcha;
	}

}
