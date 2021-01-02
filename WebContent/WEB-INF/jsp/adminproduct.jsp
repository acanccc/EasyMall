<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
	<title>商品管理主页</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link href="${ pageContext.request.contextPath }/css/orderList.css" rel="stylesheet" type="text/css">
	</head>
	<body>	
		<%@ include file = "adminhead.jsp" %>
		<br/>
		<div id="index" style="font-size: 20px;text-align:center;">
			商品管理主页
		</div>
	    <br/>
	    <div style="font-size: 15px;text-align:center;">
	    	<a href="${pageContext.request.contextPath }/admin/addprod">添加商品</a>
	    </div>
	    <br/>
	    <div style="margin: 0 auto;width:999px;">
		    <table width="999" border="0" cellpadding="0"
				cellspacing="1" style="background:#d8d8d8;color:#333333">
		    	<tr>
		    		<th width="199" height="30" align="center" valign="middle" bgcolor="#f3f3f3">商品图片</th>
					<th width="150" align="center" valign="middle" bgcolor="#f3f3f3">商品名称</th>
					<th width="100" align="center" valign="middle" bgcolor="#f3f3f3">商品单价</th>
					<th width="100" align="center" valign="middle" bgcolor="#f3f3f3">商品种类</th>
					<th width="100" align="center" valign="middle" bgcolor="#f3f3f3">商品库存</th>
					<th width="250" align="center" valign="middle" bgcolor="#f3f3f3">商品描述</th>
					<th width="100" align="center" valign="middle" bgcolor="#f3f3f3">操作</th>
		    	</tr>	
			    <c:forEach items="${products}" var="p">
			    	<tr>
			    		<td align="center" valign="middle" bgcolor="#FFFFFF">
			    			<img src="${ pageContext.request.contextPath }${p.imgurl}" width="90" height="105">
			    		</td>
			    		<td align="center" valign="middle" bgcolor="#FFFFFF">${p.name}</td>
			    		<td align="center" valign="middle" bgcolor="#FFFFFF">${p.price}</td>
			    		<td align="center" valign="middle" bgcolor="#FFFFFF">${p.category}</td>
			    		<td align="center" valign="middle" bgcolor="#FFFFFF">${p.pnum}</td>
			    		<td align="center" valign="middle" bgcolor="#FFFFFF">${p.description}</td>
			    		<td align="center" valign="middle" bgcolor="#FFFFFF">
			    			<a href="${ pageContext.request.contextPath }/admin/updateprod?id=${p.id}">修改</a><br/><br/>
			    			<a href="${ pageContext.request.contextPath }/admin/delprod?id=${p.id}">删除</a>
			    		</td>
			    	</tr>
			    </c:forEach>
		    </table>
		</div>
	    <%@ include file = "_foot.jsp" %>
	</body>
</html>