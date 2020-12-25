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
		// ����6λ�����֤��
		String captcha = RandomUtils.getMixedStr(6);
		// �ʼ�������
		String htmlBody = "��ӭע��EasyMall����������֤���䣬������֤��Ϊ��" + captcha + "��";
		// �����ʼ�
		EmailHelper.singleSend(new EmailHelper.EmailBean(
				"passerbyYSQ", email, "ע��EasyMall��������֤", htmlBody));
		return captcha;
	}

}
