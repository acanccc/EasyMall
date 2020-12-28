package easymall.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import easymall.common.CloseUtils;
import easymall.po.Orders;
import easymall.po.User;
import easymall.pojo.MyCart;
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
		return "redirect:/order/showorder"; // ��Ҫ��ת�������ض���
	}
	
	/**
	 * �ҵĶ���ҳ��
	 */
	@GetMapping("/showorder")
	public String myOrder(HttpSession session, Model model) {
		User user = (User) session.getAttribute("user");
		List<Orders> myAllOrders = orderService.getMyAllOrders(user.getId());
		model.addAttribute("myAllOrders", myAllOrders);
		return "order_list";
	}
	
	/**
	 * ���ļ���ʽ�����ҵĶ����б�
	 * @throws FileNotFoundException 
	 */
	@GetMapping("/export")
	public void export(HttpSession session, HttpServletRequest request, 
			HttpServletResponse response) throws FileNotFoundException {
		System.out.println("------------???????");
		User user = (User) session.getAttribute("user");
		// ��ȡ�ҵ����ж�����Ϣ
		List<Orders> myAllOrders = orderService.getMyAllOrders(user.getId());
		//String realPath = request.getServletContext().getRealPath("/WEB-INF/");
		// �滻ͼƬ·��
		// ·���в�����"."������ᱨ������easypoi��ܱ��������
		/*
		for (Orders order : myAllOrders) {
			List<OrderItemInfoRspVo> itemList = order.getItemList();
			for (OrderItemInfoRspVo item: itemList) {
				//System.out.println(realPath + item.getImgUrl().substring(1));
				item.setImgUrl("D:/" + item.getImgUrl().substring(1));
			}
		}
		*/
		
		ExportParams exportParams = new ExportParams("�ҵĶ����б�", "�����б�");
		// org.apache.poi.ss.usermodel.Workbook
		Workbook workbook = ExcelExportUtil.exportExcel(exportParams, Orders.class, myAllOrders);
		
		OutputStream outStream = null;
		try {
			String fileName = user.getUsername() + "�Ķ����б�.xls";
			fileName = URLEncoder.encode(fileName, "UTF-8");  // ��ֹ�ļ�����������
			outStream = response.getOutputStream();
			
			// setHeader("Content-Type", "application/octet-stream");
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
						
			workbook.write(outStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		CloseUtils.close(outStream, workbook);
	}
	
	/*
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
	*/
	
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
	@RequestMapping("/receiveorder")
	public String receiveorder(String id,Model model) {
		
		orderService.receiveorder(id);
		return "redirect:/order/showorder";
	}
	@RequestMapping("/maporder")
	public String maporder(String address,Model model) {
		
		model.addAttribute("address", address);
		return "map";
	}
	
}
