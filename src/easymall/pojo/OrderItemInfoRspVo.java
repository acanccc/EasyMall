package easymall.pojo;

import java.io.Serializable;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;

/**
 * 购物车、订单包含（显示）的商品数据
 * 
 * @author	passerbyYSQ
 * @date	2020-12-10 20:01:59
 */
@ExcelTarget("orderItemInfo")
public class OrderItemInfoRspVo implements Serializable {
	
	// 商品id
	@ExcelIgnore // 不需要导出
	private String prodId;
	
	// 商品名称
	@Excel(name = "商品名称", orderNum = "0")
	private String prodName; 
	
	// 商品封面图
	// type =2 该字段类型为图片,imageType=1 (默认可以不填),表示从file读取
	// 字段类型是个字符串类型 可以用相对路径也可以用绝对路径,绝对路径优先依次获取
	@Excel(name = "商品主图", type = 2, width = 12, orderNum = "1")
	private String imgUrl;
	
	// 商品单价
	@Excel(name = "商品单价", orderNum = "2")
	private Double price;
	
	// 购买数量
	@Excel(name = "购买数量", orderNum = "3")
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

