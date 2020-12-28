<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${ pageContext.request.contextPath }/css/orderList.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		.float-right {
			display: block;
			float: right;
		}
		.clearfix:after{/*伪元素是行内元素 正常浏览器清除浮动方法*/
	        content: "";
	        display: block;
	        height: 0;
	        clear:both;
	        visibility: hidden;
    }
	</style>
</head>
<body>
<%@ include file = "_head.jsp" %>

<div style="margin: 0 auto; width:999px;">
	<c:if test="${empty myAllOrders }">
		<div id="no_order_info">
		您还没有添加任何订单！
		</div>
		<div style="margin: 12px 0;" class="clearfix">
			<a href="${ pageContext.request.contextPath }/order/export">
				<input type="button" value="导出为Excel" class="float-right" />
			</a>
		</div>
	</c:if>
	
	<!-- 模版数据 -start -->
	<c:forEach items="${myAllOrders }" var="orderInfo">
	<div>
		<dl class="Order_information">
			<dt>
				<h3>订单信息</h3>
			</dt>
			<dd style="line-height: 26px;">
				订单编号：${orderInfo.id }
				<br />
				下单时间：${orderInfo.ordertime }
				<br /> 
				订单金额：${orderInfo.money }
				<br /> 
				支付状态：
					<c:if test="${orderInfo.paystate==0}">
						<font color="red">未支付</font>&nbsp;&nbsp;
						<a href="${ pageContext.request.contextPath }/order/delorder?id=${orderInfo.id}">
							<img src="${ pageContext.request.contextPath }/img/orderList/sc.jpg" width="69" height="19"/>
						</a>
						&nbsp;
				 		<a href="${ pageContext.request.contextPath }/order/payorder?id=${orderInfo.id}"> 
					 		<img src="${ pageContext.request.contextPath }/img/orderList/zx.jpg" width="69" height="19">
						</a>
					</c:if>
					<c:if test="${orderInfo.paystate==1}">
						<font color="#3CB371">已支付,等待商家发货</font>&nbsp;
					</c:if>
					<c:if test="${orderInfo.paystate==2}">
						商家已发货,待签收&nbsp;
						<a href="${ pageContext.request.contextPath }/order/receiveorder?id=${orderInfo.id}"> 
					 		<font color="red">确认收货</font>
						</a>
					</c:if>
					<c:if test="${orderInfo.paystate==3}">
						<font color="blue">签收成功！</font>&nbsp;
						
					</c:if>
						
						<br /> 
				所属用户：${orderInfo.username }
				<br/> 
				收货地址：${orderInfo.receiverinfo}   
				   <a href="${ pageContext.request.contextPath }/order/maporder?address=${orderInfo.receiverinfo}"> 
					 		<font color="blue">查看地图</font>
						</a>
				<br/> 
				支付方式：在线支付
			</dd>
		</dl>

		<table width="999" border="0" cellpadding="0"
			cellspacing="1" style="background:#d8d8d8;color:#333333">
			<tr>
				<th width="276" height="30" align="center" valign="middle" bgcolor="#f3f3f3">商品图片</th>
				<th width="247" align="center" valign="middle" bgcolor="#f3f3f3">商品名称</th>
				<th width="231" align="center" valign="middle" bgcolor="#f3f3f3">商品单价</th>
				<th width="214" align="center" valign="middle" bgcolor="#f3f3f3">购买数量</th>
				<th width="232" align="center" valign="middle" bgcolor="#f3f3f3">总价</th>
			</tr>
			<c:forEach items="${orderInfo.itemList }" var="item">
			<tr>
				<td align="center" valign="middle" bgcolor="#FFFFFF">
					<img src="${ pageContext.request.contextPath }${item.imgUrl}" width="90" height="105">
				</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${item.prodName}</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${item.price}元</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${item.buyNum}件</td>
				<td align="center" valign="middle" bgcolor="#FFFFFF">${item.price * item.buyNum}元</td>
			</tr>
			</c:forEach>
		</table>
		<div class="Order_price">${orderInfo.money}元</div>
	</div>
	</c:forEach>
</div>	
	<!-- 模版数据 -end -->
	<%@ include file = "_foot.jsp" %>
</body>
</html>
