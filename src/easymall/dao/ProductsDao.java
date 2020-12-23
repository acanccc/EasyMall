package easymall.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Products;

@Repository("productsDao")
@Mapper
public interface ProductsDao {
	public List<String> allcategorys();
	public List<Products> prodlist(Map<String,Object> map);
	
	public Products oneProduct(String pid);

	public List<Products> proclass(String category);
}
