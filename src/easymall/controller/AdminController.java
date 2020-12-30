package easymall.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import easymall.po.Admin;
import easymall.po.Category;
import easymall.po.OrderItem;
import easymall.po.Orders;
import easymall.po.Products;
import easymall.po.User;
import easymall.pojo.MyProducts;
import easymall.pojo.OrderInfo;
import easymall.service.AdminService;
import easymall.service.CartService;
import easymall.service.OrderService;
import easymall.service.ProductsService;
import easymall.service.UserService;

@Controller("adminController")
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private CartService cartService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private ProductsService productsService;
	
	@RequestMapping("/login")
	public String login() {
		return "adminlogin";
	}
	
	@Autowired
	private AdminService adminService;
	@RequestMapping("/dologin")
	public String dologin(Admin admin,HttpSession session,Model model) {
		Admin madmin=adminService.login(admin);
		if(madmin!=null) {
			session.setAttribute("admin", madmin);
			return "adminindex";
		}else {
			model.addAttribute("message","用户名密码错误！");
			return "adminlogin";
		}
		
	}
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/index";
	}
	
	@RequestMapping("/showorder")
	public String showorder(HttpSession session,Model model) {
		List<OrderInfo> orderInfoList=findAllOrderInfo();
		model.addAttribute("orderInfos",orderInfoList);
		return "adminorder_list";
	}
	
	private List<OrderInfo> findAllOrderInfo() {
		List<OrderInfo> orderInfoList=new ArrayList<OrderInfo>();
		List<Orders> orderList=orderService.findAllOrder();
		
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
	
	@RequestMapping("/sendorder")
	public String sendorder(String id,Model model) {
		
		orderService.sendorder(id);
		return "redirect:/admin/showorder";
	}
	
	@RequestMapping("/addprod")
	public String addprod(Model model) {
//		查找商品表中所有的商品类别
		List<Category> categorys=productsService.allcategorys();
		model.addAttribute("categorys",categorys);
		model.addAttribute("myproducts",new MyProducts());
		return "adminproduct";
	}
	
	@RequestMapping("/save")
	public String save(@Valid @ModelAttribute MyProducts myproducts, HttpServletRequest request,Model model)
	throws Exception{
		String msg=productsService.save(myproducts,request);
		model.addAttribute("msg",msg);
		return "redirect:/admin/addprod";
	}
	
}
