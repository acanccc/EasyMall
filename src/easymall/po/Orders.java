package easymall.po;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelIgnore;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import easymall.pojo.OrderItemInfoRspVo;

@ExcelTarget("order")
public class Orders implements Serializable {
	@Excel(name = "订单号", orderNum = "0", needMerge = true)
	private String id;
	
	@Excel(name = "订单总金额", orderNum = "1", needMerge = true)
	private Double money;
	
	@Excel(name = "收货信息", orderNum = "2", needMerge = true)
	private String receiverinfo;
	
	@Excel(name = "订单状态", orderNum = "3", replace = { "未支付_0", "已支付，待发货_1" }, needMerge = true)
	private Integer paystate;
	
	@Excel(name = "下单时间", orderNum = "4", format = "yyyy-MM-dd HH:mm:ss", needMerge = true)
	private Timestamp ordertime ;
	
	// 不需要导出
	@ExcelIgnore
	private Integer user_id;
	
	@Excel(name = "用户名", orderNum = "5", needMerge = true)
	private String username;
	
	// 属于该订单的商品信息
	@ExcelCollection(name = "订单包含的商品信息", orderNum = "6")
    List<OrderItemInfoRspVo> itemList = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public String getReceiverinfo() {
		return receiverinfo;
	}
	public void setReceiverinfo(String receiverinfo) {
		this.receiverinfo = receiverinfo;
	}
	public Integer getPaystate() {
		return paystate;
	}
	public void setPaystate(Integer paystate) {
		this.paystate = paystate;
	}
	public Timestamp getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Timestamp ordertime) {
		this.ordertime = ordertime;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Orders(String id, Double money, String receiverinfo, Integer paystate, Timestamp ordertime, Integer user_id,
			String username) {
		super();
		this.id = id;
		this.money = money;
		this.receiverinfo = receiverinfo;
		this.paystate = paystate;
		this.ordertime = ordertime;
		this.user_id = user_id;
		this.username = username;
	}
	public Orders() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public List<OrderItemInfoRspVo> getItemList() {
		return itemList;
	}

	public void setItemList(List<OrderItemInfoRspVo> itemList) {
		this.itemList = itemList;
	}
	
	
}
