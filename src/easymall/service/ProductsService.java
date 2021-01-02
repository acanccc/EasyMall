package easymall.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import easymall.po.Category;
import easymall.po.Products;
import easymall.pojo.MyProducts;
import easymall.pojo.MyProducts2;
import easymall.pojo.MyProducts3;
import easymall.pojo.ProdListReqParamsVo;

public interface ProductsService {
	public List<Category> allcategorys();
	
	// 根据多条件检索商品
	public List<Products> getProdListByConds(ProdListReqParamsVo params);
	
	//public List<Products> prodlist(Map<String,Object> map);

	public Products oneProduct(String pid);

	public List<Products> proclass(Integer proclass);
	
	public void save(MyProducts myproducts,HttpServletRequest request);
	
	public List<MyProducts2> allProducts();
	
	public void delProdById(String id);
	
	public void updateProdById(MyProducts3 myproducts, HttpServletRequest request);
	
	public MyProducts2 findProductById(String id);
	
}
