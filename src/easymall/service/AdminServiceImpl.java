package easymall.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.AdminDao;
import easymall.po.Admin;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao admindao;
	@Override
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		return admindao.login(admin);
	}

}
