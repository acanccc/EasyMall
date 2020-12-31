package easymall.pojo;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.util.ObjectUtils;

/**
 * 商品列表接口的请求参数
 * 
 * @author	passerbyYSQ
 * @date	2020-11-30 19:49:08
 */
public class ProdListReqParamsVo {
	
	// 商品名称关键词。可以为空
	private String prodName;
	
	// 分类名字。可以为空，为空时表示所有分类
	private Integer cateId;
	
	// 最低价格。不允许为负数，且 minPrice <= maxPrice 
	private Double minPrice;
	
	// 最高价格。不允许为负数，且 minPrice <= maxPrice 
	private Double maxPrice;
	
	/**
	 * 将有效的参数拼接成url字符串，用于拼接到url后面。切换页码时携带
	 */
	public String joinUrlParams() {
		StringBuilder urlParamsStr = new StringBuilder("");
		if (prodName != null) { // 可以为空串
			String encodedProdName = prodName;
			try {
				// 对中文进行url编码
				encodedProdName = URLEncoder.encode(prodName, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			urlParamsStr.append("&prodName=").append(encodedProdName);
		}
		if (!ObjectUtils.isEmpty(cateId)) { 
			urlParamsStr.append("&cateId=").append(cateId);
		}
		if (!ObjectUtils.isEmpty(minPrice)) {
			urlParamsStr.append("&minPrice=").append(minPrice);
		}
		if (!ObjectUtils.isEmpty(maxPrice)) {
			urlParamsStr.append("&maxPrice=").append(maxPrice);
		}
		
		return urlParamsStr.toString();
	}

	public String getProdName() {
		return prodName;
	}
	
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public Integer getCateId() {
		return cateId;
	}

	public void setCateId(Integer cateId) {
		this.cateId = cateId;
	}

	public Double getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(Double minPrice) {
		this.minPrice = minPrice;
	}

	public Double getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(Double maxPrice) {
		this.maxPrice = maxPrice;
	}

}
