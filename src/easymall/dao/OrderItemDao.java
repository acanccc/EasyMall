package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.OrderItem;
import easymall.pojo.OrderItemInfoRspVo;

@Repository("orderItemDao")
@Mapper
public interface OrderItemDao {
	
	// ��ѯĳ�������е���Ʒ��Ϣ
	List<OrderItemInfoRspVo> selectOrderItems(String orderId);

	void addOrderItem(OrderItem orderItem);
	List<OrderItem> orderitem(String order_id);
	void delorderitem(String id);
}
