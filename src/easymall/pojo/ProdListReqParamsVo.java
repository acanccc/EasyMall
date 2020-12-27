package easymall.pojo;

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
	private String cate;
	
	// ��ͼ۸񡣲�����Ϊ�������� minPrice <= maxPrice 
	private Double minPrice;
	
	// ��߼۸񡣲�����Ϊ�������� minPrice <= maxPrice 
	private Double maxPrice;
	
	/**
	 * ����Ч�Ĳ���ƴ�ӳ�url�ַ���������ƴ�ӵ�url���档�л�ҳ��ʱЯ��
	 */
	public String joinUrlParams() {
		StringBuilder urlParamsStr = new StringBuilder("");
		if (prodName != null) { // ����Ϊ��
			urlParamsStr.append("&prodName=").append(prodName);
		}
		if (cate != null) {
			urlParamsStr.append("&cate=").append(cate);
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

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
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

	@Override
	public String toString() {
		return "ProdListReqParamsVo [goodsName=" + prodName + ", cate=" + cate + ", minPrice=" + minPrice
				+ ", maxPrice=" + maxPrice + "]";
	}
	
}
