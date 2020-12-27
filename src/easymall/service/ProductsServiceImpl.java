package easymall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.ProductsDao;
import easymall.po.Products;
import easymall.pojo.ProdListReqParamsVo;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsDao productsDao;
	
	@Override
	public List<String> allcategorys() {
		// TODO Auto-generated method stub
		List<String> categorys=productsDao.allcategorys();
		return categorys;
	}

	@Override
	public Products oneProduct(String pid) {
		return productsDao.oneProduct(pid);
	}

	@Override
	public List<Products> proclass(String proclass) {
		return productsDao.proclass(proclass);
	}

	// 多条件查询商品列表
	@Override
	public List<Products> getProdListByConds(ProdListReqParamsVo params) {
		return productsDao.selectProdsByConds(params);
	}

}
