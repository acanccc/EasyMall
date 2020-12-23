package easymall.service;

import java.util.List;
import java.util.Map;

import easymall.po.Products;

public interface ProductsService {
	public List<String> allcategorys();
	public List<Products> prodlist(Map<String,Object> map);

	public Products oneProduct(String pid);

	public List<Products> proclass(String proclass);
}
