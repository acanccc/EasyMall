<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div id="common_head">
	<div id="line1">
		<div id="content">
		
		 
	
		 	 
		 <c:if test="${ !(empty sessionScope.admin) }">
		 	 	"欢迎${ sessionScope.admin.username }回来~~&nbsp;"
		 	<a href="${ pageContext.request.contextPath }/admin/logout">退出</a>		 
		 </c:if>
		 
		
		</div>	
	</div>
	<div id="line2">
		<img id="logo" src="${ pageContext.request.contextPath }/img/head/logo.jpg"/>
		
		<img id="erwm"   src="${ pageContext.request.contextPath }/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul >
				<li style="width:200px;"><a href="${ pageContext.request.contextPath }/admin/addprod">商品管理</a></li>
				<li style="width:200px;"><a href="#">商品类别管理</a></li>
				<li style="width:200px;"><a href="${pageContext.request.contextPath }/admin/showorder">订单管理</a></li>
				<li style="width:200px;"><a href="#">销售榜单</a></li>
				<li style="width:200px;"><a href="#">阿巴阿巴</a></li>
				<li style="width:200px;"><a href="#">阿巴阿巴</a></li>
				
			</ul>
		</div>
	</div>
</div>