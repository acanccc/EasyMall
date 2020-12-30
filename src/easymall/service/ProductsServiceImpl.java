package easymall.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.ProductsDao;
import easymall.po.Category;
import easymall.po.Products;
import easymall.pojo.MyProducts;
import easymall.pojo.ProdListReqParamsVo;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsDao productsDao;
	
	@Override
	public List<Category> allcategorys() {
		// TODO Auto-generated method stub
		List<Category> categorys=productsDao.allcategorys();
		return categorys;
	}

	@Override
	public Products oneProduct(String pid) {
		return productsDao.oneProduct(pid);
	}

	@Override
	public List<Products> proclass(Integer proclass) {
		return productsDao.proclass(proclass);
	}

	// 多条件查询商品列表
	@Override
	public List<Products> getProdListByConds(ProdListReqParamsVo params) {
		return productsDao.selectProdsByConds(params);
	}

	@Override
	public String save(MyProducts myproducts, HttpServletRequest request) {
		String originName=myproducts.getImgurl().getOriginalFilename();
		String extName=originName.substring(originName.lastIndexOf("."));
		if(!(extName.equalsIgnoreCase(".jpg")||extName.equalsIgnoreCase(".png")||extName.equalsIgnoreCase(".gif"))) {
			return "图片后缀不合法！";
		}
		try {
			BufferedImage bufImage=ImageIO.read(myproducts.getImgurl().getInputStream());
			bufImage.getHeight();
			bufImage.getWidth();
		} catch (Exception e) {
			return "该文件不是图片";
		}
		String imgurl="";
		for(int i=0;i<8;i++) {
			imgurl+="/"+Integer.toHexString(new Random().nextInt(16));
		}
		String realpath=request.getServletContext().getRealPath("/WEB-INF");
		realpath+="/upload";
		System.out.println(realpath+imgurl);
		File file=new File(realpath+imgurl,originName);
		if(!file.exists()) {
			file.mkdirs();
		}
		try {
			myproducts.getImgurl().transferTo(file);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		imgurl="/upload"+imgurl+"/"+originName;
		String id=UUID.randomUUID().toString();
		Products products=new Products();
		products.setId(id);
		products.setName(myproducts.getName());
		products.setCategory(myproducts.getCategory());
		products.setPrice(myproducts.getPrice());
		products.setPnum(myproducts.getPnum());
		products.setImgurl(imgurl);
		products.setDescription(myproducts.getDescription());
		if(productsDao.findByImgurl(products.getImgurl())==null) {
			productsDao.save(products);
		}else {
			String fname=imgurl.substring(0,imgurl.lastIndexOf("."));
			imgurl=fname+System.currentTimeMillis()+extName;
			products.setImgurl(imgurl);
			System.out.println(products.getImgurl());
			productsDao.save(products);
		}
		return "商品添加成功";
	}

}
