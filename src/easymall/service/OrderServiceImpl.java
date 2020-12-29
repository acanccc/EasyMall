package easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import easymall.dao.CartDao;
import easymall.dao.OrderDao;
import easymall.dao.OrderItemDao;
import easymall.po.OrderItem;
import easymall.po.Orders;
import easymall.pojo.MyCart;
import easymall.pojo.OrderItemInfoRspVo;

@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CartDao cartDao;
	@Autowired
	private OrderItemDao orderItemDao;
	@Autowired
	private OrderDao orderDao;

	/**
	 * ��ѯ�ҵ����ж�����Ϣ
	 */
	@Override
	public List<Orders> getMyAllOrders(Integer userId) {
		// ��ѯ����ǰ�û������ж������ݲ����Ƿ�ҳ
		List<Orders> myOrderList = orderDao.findOrderByUserId(userId);
		
		for (Orders order : myOrderList) {
			// ��ѯ���ö�����������Ʒ��Ϣ
			List<OrderItemInfoRspVo> itemList = orderItemDao.selectOrderItems(order.getId());
			order.setItemList(itemList);
		}
		
		return myOrderList;
	}
	

	@Override
	public List<Orders> findOrderByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return orderDao.findOrderByUserId(user_id);
	}

	@Override
	public List<OrderItem> orderitem(String order_id) {
		// TODO Auto-generated method stub
		return orderItemDao.orderitem(order_id);
	}

	@Override
	public void addOrder(String cartIds, Orders myOrder) {
		String[] arrCartIds=cartIds.split(",");
		Double sum=0.0;
		for(String cartID:arrCartIds) {
			Integer cid=Integer.parseInt(cartID);
			MyCart mycart=cartDao.findByCartID(cid);
			String pid=mycart.getPid();
			int buynum=mycart.getNum();
			Double price=mycart.getPrice();
			sum+=buynum*price;
			OrderItem orderItem=new OrderItem();
			orderItem.setOrder_id(myOrder.getId());
			orderItem.setProduct_id(pid);
			orderItem.setBuynum(buynum);
			orderItemDao.addOrderItem(orderItem);
			cartDao.delCart(cid);
		} 
		myOrder.setMoney(sum);
		orderDao.addOrder(myOrder);
		
	}

	@Override
	public void delorder(String id) {
		// TODO Auto-generated method stub
		orderItemDao.delorderitem(id);
		orderDao.delorder(id);
	}

	@Override
	public void payorder(String id) {
		// TODO Auto-generated method stub
		orderDao.payorder(id);
	}

	@Override
	public List<Orders> findAllOrder() {
		// TODO Auto-generated method stub
		return orderDao.findAllOrder();
	}

	@Override
	public void sendorder(String id) {
		// TODO Auto-generated method stub
		orderDao.sendorder(id);
	}


	@Override
	public void receiveorder(String id) {
		// TODO Auto-generated method stub
		orderDao.receiveorder(id);
	}


}
