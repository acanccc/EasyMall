package easymall.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	 * �ýӿڱ���֧��post��get��ʽ����
	 * ��Ϊ���ύ��post��ʽ������һ�δ�ҳ��͵��ҳ���л�ҳ�涼��get��ʽ
	 * 
	 * @param page	��ǰҳ����ҳ������������ǰ�˲�������Ĭ��Ϊ1
	 * @param count		ÿҳ��ʾ��������¼����ҳ������������ǰ�˲�������Ĭ��Ϊ2
	 */
	@RequestMapping("/prodlist") 
	public String prodlist(@ModelAttribute("params") ProdListReqParamsVo params, 
			Integer page, Integer count, Model model) {
		// �������;���
		if (!ObjectUtils.isEmpty(params.getMinPrice()) &&
				!ObjectUtils.isEmpty(params.getMaxPrice())) {
			// ���� minPriceΪ�Ǹ��� 
			if (params.getMinPrice() < 0) {
				params.setMinPrice(0d);
			}
			// ����Ϊ minPrice <= maxPrice
			if (params.getMinPrice() > params.getMaxPrice()) {
				double min = params.getMinPrice();
				params.setMinPrice(params.getMaxPrice());
				params.setMaxPrice(min);
			}
		}
		// curPage �Ƿ�Խ�磬�ɲ���Ҫ�жϣ�PageHelper�ڲ����жϲ�����
		if (ObjectUtils.isEmpty(page)) {
			page = 1;
		}
		if (ObjectUtils.isEmpty(count) || count <= 0) {
			count = 2;
		}
		
		// ��ѯ���з���
		List<String> cates = productsService.allcategorys();
		
		// ����PageHelper���з�ҳ
		// ���������������ĵ�һ��MyBatis ��ѯ�����ᱻ���з�ҳ
		PageHelper.startPage(page, count);
		
		// ��ѯ����
		List<Products> prodList = productsService.getProdListByConds(params);
		
		// ��ȡ���ַ�ҳ����
		PageInfo<Products> pageInfo = new PageInfo<>(prodList);
		// ����Ч�Ĳ���ƴ�ӳ�url�ַ���������ƴ�ӵ�url���档�л�ҳ��ʱЯ��
		String urlParamsStr = params.joinUrlParams();
		
		model.addAttribute("cates", cates);  // ��������
		model.addAttribute("prodList", prodList); // ��Ʒ�б�����
		model.addAttribute("page", pageInfo.getPageNum()); // ����ǰ�˱�������ĵ�ǰҳ
		model.addAttribute("count", count);  // ÿһҳ��ʾ��������¼
		model.addAttribute("total", pageInfo.getTotal()); // �ܼ�¼��
		model.addAttribute("urlParamsStr", urlParamsStr);
		
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
