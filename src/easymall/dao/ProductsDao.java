package easymall.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import easymall.po.Products;
import easymall.pojo.ProdListReqParamsVo;

@Repository("productsDao")
@Mapper
public interface ProductsDao {
	public List<String> allcategorys();
	
	// ��������ѯ��Ʒ�б�
	List<Products> selectProdsByConds(ProdListReqParamsVo params);
	
	//public List<Products> prodlist(Map<String,Object> map);
	
	public Products oneProduct(String pid);

	public List<Products> proclass(String category);

}
