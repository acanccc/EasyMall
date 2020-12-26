package easymall.service;

import java.util.List;

import easymall.po.OrderItem;
import easymall.po.Orders;

public interface OrderService {
	
	// ��ѯ�ҵ����ж�����Ϣ
	List<Orders> getMyAllOrders(Integer userId);
		

	public void addOrder(String cartIds,Orders myOrder);
	public List<Orders> findOrderByUserId(Integer user_id);
	public List<OrderItem> orderitem(String order_id);
	public void delorder(String id);
	public void payorder(String id);
	public void sendorder(String id);
	
	public List<Orders> findAllOrder();
}
