package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Category;
import easymall.po.Products;
import easymall.pojo.ProdListReqParamsVo;

@Repository("productsDao")
@Mapper
public interface ProductsDao {
	public List<Category> allcategorys();
	
	// ��������ѯ��Ʒ�б�
	List<Products> selectProdsByConds(ProdListReqParamsVo params);
	
	//public List<Products> prodlist(Map<String,Object> map);
	
	public Products oneProduct(String pid);

	public List<Products> proclass(Integer category);
	
	public void save(Products products);
	
	public Object findByImgurl(String imgurl);
	
	public List<Products> allProducts();
	
	public void delProdById(String id);
	
	public void updateProdById(Products products);
	
	public String findUrlById(String id);

}
