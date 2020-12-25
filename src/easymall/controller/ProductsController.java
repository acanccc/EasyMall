package easymall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import easymall.po.Products;
import easymall.service.ProductsService;

@Controller("productsController")
public class ProductsController {

	@Autowired
	private ProductsService productsService;
	@RequestMapping("/prodlist")
	public String prodlist(String name,String category,String minprice,
			 String maxprice,Model model) {
		List<String> categorys=productsService.allcategorys();
		model.addAttribute("categorys", categorys);
		
		double _minPrice=0;
		double _maxPrice=Double.MAX_VALUE;
		
		String reg="^\\d+$";
		if (minprice!=null&&!"".equals(minprice.trim())&&minprice.matches(reg)){
			_minPrice=Double.parseDouble(minprice);
			
		}
		if (maxprice!=null&&!"".equals(maxprice.trim())&&maxprice.matches(reg)){
			
			if (Double.parseDouble(maxprice)>=_minPrice){
				_maxPrice=Double.parseDouble(maxprice);
				
			}
			
			
		}
		
		Map<String, Object> map=new HashMap<>();
		map.put("name",name);
		map.put("category",category);
		map.put("minprice",_minPrice);
		map.put("maxprice",_maxPrice);
		
		List<Products> products=productsService.prodlist(map);
		model.addAttribute("name", name);
		model.addAttribute("minprice", _minPrice);
		model.addAttribute("maxprice",_maxPrice);
		model.addAttribute("products", products);
		
		return "prod_list";
	}
	
	@RequestMapping("/prodInfo")
	public String prodInfo(String pid,Model model) {
		Products product=productsService.oneProduct(pid);
		model.addAttribute("product",product);
		return "prod_info";
	}
	
	@RequestMapping(value = "/prodclass/{proclass}",method = RequestMethod.GET)
	public String prodclass(@PathVariable String proclass,Model model)
	{
		List<Products> products=productsService.proclass(proclass);
		model.addAttribute("products", products);
		//return "forward:/WEB-INF/jsp/prod_list.jsp";
		return "prod_list";
	}
	
	
}
