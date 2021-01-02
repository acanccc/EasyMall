package easymall.pojo;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * ���ﳵ��������������ʾ������Ʒ����
 * 
 * @author	passerbyYSQ
 * @date	2020-12-10 20:01:59
 */
@ExcelTarget("orderItemInfo")
public class OrderItemInfoRspVo implements Serializable {
	
	// ��Ʒid
	@ExcelIgnore // ����Ҫ����
	private String prodId;
	
	// ��Ʒ����
	@Excel(name = "��Ʒ����", orderNum = "0")
	private String prodName; 
	
	@Excel(name = "��������", orderNum = "1")
	private String cateName;
	
	// ��Ʒ����ͼ
	// type =2 ���ֶ�����ΪͼƬ,imageType=1 (Ĭ�Ͽ��Բ���),��ʾ��file��ȡ
	// �ֶ������Ǹ��ַ������� ���������·��Ҳ�����þ���·��,����·���������λ�ȡ
	@Excel(name = "��Ʒ��ͼ", type = 2, width = 12, orderNum = "2")
	private String imgUrl;
	
	// ��Ʒ����
	@Excel(name = "��Ʒ����", orderNum = "3")
	private Double price;
	
	// ��������
	@Excel(name = "��������", orderNum = "4")
	private Integer buyNum;

	public String getProdId() {
		return prodId;
	}

	public void setProdId(String prodId) {
		this.prodId = prodId;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getBuyNum() {
		return buyNum;
	}

	public void setBuyNum(Integer buyNum) {
		this.buyNum = buyNum;
	}

}

