package easymall.pojo;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.util.ObjectUtils;

/**
 * ��Ʒ�б�ӿڵ��������
 * 
 * @author	passerbyYSQ
 * @date	2020-11-30 19:49:08
 */
public class ProdListReqParamsVo {
	
	// ��Ʒ���ƹؼ��ʡ�����Ϊ��
	private String prodName;
	
	// �������֡�����Ϊ�գ�Ϊ��ʱ��ʾ���з���
	private Integer cateId;
	
	// ��ͼ۸񡣲�����Ϊ�������� minPrice <= maxPrice 
	private Double minPrice;
	
	// ��߼۸񡣲�����Ϊ�������� minPrice <= maxPrice 
	private Double maxPrice;
	
	/**
	 * ����Ч�Ĳ���ƴ�ӳ�url�ַ���������ƴ�ӵ�url���档�л�ҳ��ʱЯ��
	 */
	public String joinUrlParams() {
		StringBuilder urlParamsStr = new StringBuilder("");
		if (prodName != null) { // ����Ϊ�մ�
			String encodedProdName = prodName;
			try {
				// �����Ľ���url����
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
