package easymall.service;

import java.util.List;

import easymall.po.Category;
import easymall.po.Products;
import easymall.pojo.ProdListReqParamsVo;

public interface ProductsService {
	public List<Category> allcategorys();
	
	// ���ݶ�����������Ʒ
	public List<Products> getProdListByConds(ProdListReqParamsVo params);
	
	//public List<Products> prodlist(Map<String,Object> map);

	public Products oneProduct(String pid);

	public List<Products> proclass(Integer proclass);
}
