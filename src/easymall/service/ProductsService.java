package easymall.service;

import java.util.List;

import easymall.po.Products;
import easymall.pojo.ProdListReqParamsVo;

public interface ProductsService {
	public List<String> allcategorys();
	
	// 根据多条件检索商品
	public List<Products> getProdListByConds(ProdListReqParamsVo params);
	
	//public List<Products> prodlist(Map<String,Object> map);

	public Products oneProduct(String pid);

	public List<Products> proclass(String proclass);
}
