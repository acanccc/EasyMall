package easymall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import easymall.po.Products;
import easymall.pojo.ProdListReqParamsVo;
import easymall.service.ProductsService;

@Controller("productsController")
public class ProductsController {

	@Autowired
	private ProductsService productsService;
	
	/**
	 * 该接口必须支持post和get方式访问
	 * 因为表单提交是post方式，而第一次打开页面和点击页码切换页面都是get方式
	 * 
	 * @param page	当前页。分页所需参数，如果前端不传，则默认为1
	 * @param count		每页显示多少条记录。分页所需参数，如果前端不传，则默认为2
	 */
	@RequestMapping("/prodlist") 
	public String prodlist(@ModelAttribute("params") ProdListReqParamsVo params, 
			Integer page, Integer count, Model model) {
		// 参数检查和纠正
		if (!ObjectUtils.isEmpty(params.getMinPrice()) &&
				!ObjectUtils.isEmpty(params.getMaxPrice())) {
			// 纠正 minPrice为非负数 
			if (params.getMinPrice() < 0) {
				params.setMinPrice(0d);
			}
			// 纠正为 minPrice <= maxPrice
			if (params.getMinPrice() > params.getMaxPrice()) {
				double min = params.getMinPrice();
				params.setMinPrice(params.getMaxPrice());
				params.setMaxPrice(min);
			}
		}
		// curPage 是否越界，可不需要判断，PageHelper内部会判断并纠正
		if (ObjectUtils.isEmpty(page)) {
			page = 1;
		}
		if (ObjectUtils.isEmpty(count) || count <= 0) {
			count = 2;
		}
		
		// 查询所有分类
		List<String> cates = productsService.allcategorys();
		
		// 调用PageHelper进行分页
		// 紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页
		PageHelper.startPage(page, count);
		
		// 查询数据
		List<Products> prodList = productsService.getProdListByConds(params);
		
		// 获取各种分页属性
		PageInfo<Products> pageInfo = new PageInfo<>(prodList);
		// 将有效的参数拼接成url字符串，用于拼接到url后面。切换页码时携带
		String urlParamsStr = params.joinUrlParams();
		
		model.addAttribute("cates", cates);  // 分类数据
		model.addAttribute("prodList", prodList); // 商品列表数据
		model.addAttribute("page", pageInfo.getPageNum()); // 传给前端被修正后的当前页
		model.addAttribute("count", count);  // 每一页显示多少条记录
		model.addAttribute("total", pageInfo.getTotal()); // 总记录数
		model.addAttribute("urlParamsStr", urlParamsStr);
		
		return "prod_list";
	}
	
	@RequestMapping("/prodInfo")
	public String prodInfo(String pid,Model model) {
		Products product=productsService.oneProduct(pid);
		model.addAttribute("product",product);
		return "prod_info";
	}
	
	/*
	@RequestMapping(value = "/prodclass/{proclass}",method = RequestMethod.GET)
	public String prodclass(@PathVariable String proclass,Model model)
	{
		List<Products> products=productsService.proclass(proclass);
		model.addAttribute("products", products);
		//return "forward:/WEB-INF/jsp/prod_list.jsp";
		return "prod_list";
	}
	*/
	
	
}
