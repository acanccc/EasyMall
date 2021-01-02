package easymall.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import easymall.dao.CategoryDao;
import easymall.dao.ProductsDao;
import easymall.po.Category;
import easymall.po.Products;
import easymall.pojo.MyProducts;
import easymall.pojo.MyProducts2;
import easymall.pojo.MyProducts3;
import easymall.pojo.ProdListReqParamsVo;

@Service("productsService")
public class ProductsServiceImpl implements ProductsService {

	@Autowired
	private ProductsDao productsDao;
	
	@Autowired
	private CategoryDao categoryDao;
	
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
	public void save(MyProducts myproducts, HttpServletRequest request) {
		String originName=myproducts.getImgurl().getOriginalFilename();
		String extName=originName.substring(originName.lastIndexOf("."));
		if(!(extName.equalsIgnoreCase(".jpg")||extName.equalsIgnoreCase(".png")||extName.equalsIgnoreCase(".gif"))) {
			return;
		}
		BufferedImage bufImage;
		try {
			bufImage = ImageIO.read(myproducts.getImgurl().getInputStream());
			bufImage.getHeight();
			bufImage.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String imgurl=getUrl(myproducts, originName, request);
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
	}

	@Override
	public List<MyProducts2> allProducts() {
		List<Products> products=productsDao.allProducts();
		List<MyProducts2> products2=new ArrayList<MyProducts2>();
		for(Products product:products) {
			MyProducts2 myproduct=new MyProducts2();
			myproduct.setId(product.getId());
			myproduct.setName(product.getName());
			myproduct.setPnum(product.getPnum());
			myproduct.setPrice(product.getPrice());
			myproduct.setCategory(categoryDao.findNameById(product.getCategory()));
			myproduct.setImgurl(product.getImgurl());
			myproduct.setDescription(product.getDescription());
			products2.add(myproduct);
		}
		return products2;
	}

	@Override
	public void delProdById(String id) {
		productsDao.delProdById(id);
	}

	@Override
	public void updateProdById(MyProducts3 myproducts, HttpServletRequest request) {
		Products products=new Products();
		products.setId(myproducts.getId());
		products.setName(myproducts.getName());
		products.setPnum(myproducts.getPnum());
		products.setCategory(myproducts.getCategory());
		products.setPrice(myproducts.getPrice());
		products.setDescription(myproducts.getDescription());
		String originName=myproducts.getImgurl().getOriginalFilename();
		if(originName==null||originName=="") {
			products.setImgurl(productsDao.findUrlById(myproducts.getId()));
		}else {
			String extName=originName.substring(originName.lastIndexOf("."));
			if(!(extName.equalsIgnoreCase(".jpg")||extName.equalsIgnoreCase(".png")||extName.equalsIgnoreCase(".gif"))) {
				return;
			}
			try {
				BufferedImage bufImage=ImageIO.read(myproducts.getImgurl().getInputStream());
				bufImage.getHeight();
				bufImage.getWidth();
			} catch (Exception e) {
				e.printStackTrace();
			}
			MyProducts myProducts2=new MyProducts();
			myProducts2.setImgurl(myproducts.getImgurl());
			String imgurl=getUrl(myProducts2, originName, request);
			products.setImgurl(imgurl);
			if(productsDao.findByImgurl(products.getImgurl())!=null) {
				String fname=imgurl.substring(0,imgurl.lastIndexOf("."));
				imgurl=fname+System.currentTimeMillis()+extName;
				products.setImgurl(imgurl);
				System.out.println(products.getImgurl());
			}
		}
		productsDao.updateProdById(products);
	}
	
	public String getUrl(MyProducts myproducts,String originName,HttpServletRequest request) {
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
		 return imgurl;
	}

	@Override
	public MyProducts2 findProductById(String id) {
		MyProducts2 myProducts2=new MyProducts2();
		Products products=productsDao.oneProduct(id);
		myProducts2.setId(products.getId());
		myProducts2.setCategory(categoryDao.findNameById(products.getCategory()));
		myProducts2.setDescription(products.getDescription());
		myProducts2.setImgurl(products.getImgurl());
		myProducts2.setName(products.getName());
		myProducts2.setPnum(products.getPnum());
		myProducts2.setPrice(products.getPrice());
		return myProducts2;
	}

}
