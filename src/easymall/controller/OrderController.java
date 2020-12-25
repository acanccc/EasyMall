package easymall.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sun.org.apache.bcel.internal.generic.NEW;

import easymall.po.Orders;
import easymall.po.OrderItem;
import easymall.po.Products;
import easymall.po.User;
import easymall.pojo.MyCart;
import easymall.pojo.OrderInfo;
import easymall.service.CartService;
import easymall.service.OrderService;
import easymall.service.ProductsService;

@Controller("orderController")
@RequestMapping("/order")
public class OrderController extends BaseController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductsService productsService;
	@RequestMapping("order_add")
	public String order_add(String cartIds,Model model) {
		String[] arrCartIds=cartIds.split(",");
		List<MyCart> carts=new ArrayList<MyCart>();
		for(String cid:arrCartIds) {
			Integer cartID=Integer.parseInt(cid);
			MyCart cart=cartService.findByCartID(cartID);
			carts.add(cart);
		}
		model.addAttribute("carts",carts);
		model.addAttribute("cartIds",cartIds);
		return("order_add");
	}
	
	@RequestMapping("/addOrder")
	public String addOrder(String receiverinfo,String cartIds,HttpSession session) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String time=df.format(new Date());
		Timestamp timestamp=Timestamp.valueOf(time);
		User user=(User)session.getAttribute("user");
		String orderId=UUID.randomUUID().toString();
		Orders myOrder=new Orders(orderId,null,receiverinfo,0,timestamp,user.getId(),user.getUsername());
		orderService.addOrder(cartIds,myOrder);
		return "forward:/order/showorder";
	}
	
	@RequestMapping("/showorder")
	public String showorder(HttpSession session,Model model) {
		User user=(User)session.getAttribute("user");
		List<OrderInfo> orderInfoList=findOrderInfoByUserId(user.getId());
		model.addAttribute("orderInfos",orderInfoList);
		return "order_list";
	}
	
	private List<OrderInfo> findOrderInfoByUserId(Integer userId) {
		List<OrderInfo> orderInfoList=new ArrayList<OrderInfo>();
		List<Orders> orderList=orderService.findOrderByUserId(userId);
		
		for(Orders order:orderList) {
			List<OrderItem> orderItems=orderService.orderitem(order.getId());
			
			Map<Products, Integer> map=new HashMap<Products, Integer>();
			for(OrderItem orderItem:orderItems) {
				Products products=productsService.oneProduct(orderItem.getProduct_id());
				map.put(products, orderItem.getBuynum());
			}
			OrderInfo orderInfo=new OrderInfo();
			orderInfo.setOrder(order);
			orderInfo.setMap(map);
			orderInfoList.add(orderInfo);
		}
		return orderInfoList;
	}
	@RequestMapping("/delorder")
	public String delorder(String id,Model model) {
		
		orderService.delorder(id);
		return "redirect:/order/showorder";
	}
	@RequestMapping("/payorder")
	public String payorder(String id,Model model) {
		
		orderService.payorder(id);
		return "redirect:/order/showorder";
	}
	
}
