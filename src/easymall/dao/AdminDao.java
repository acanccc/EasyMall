package easymall.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Admin;

@Repository
@Mapper
public interface AdminDao {
	Admin login(Admin admin);
}
