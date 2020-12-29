package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Orders;

@Repository("orderDao")
@Mapper
public interface OrderDao {
	
	void addOrder(Orders myOrder);
	List<Orders> findOrderByUserId(Integer user_id);
	void delorder(String id);
	void payorder(String id);
	List<Orders> findAllOrder();
	void sendorder(String id);
	void receiveorder(String id);
}
