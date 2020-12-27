<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<link href="${pageContext.request.contextPath }/css/prodList.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath }/css/pageHelper.css" rel="stylesheet" type="text/css">
	
	<style>
		.pagehelper {
			text-align: center;
		}
	</style>
	
</head>
<body>

	<%@ include file="_head.jsp" %>
	
	<div id="content">
		<div id="search_div">
			<form method="post" action="${pageContext.request.contextPath}/prodlist">
				<span class="input_span">商品名：<input type="text" name="prodName" value="${params.prodName}"/></span>
				<span class="input_span">商品种类：</span>
				<select name="cate">
					<option value="">不限</option>
					<c:forEach items="${cates}" var="cate">
						<option value="${cate}" <c:if test="${cate==params.cate}">selected</c:if>>${cate}</option>
					</c:forEach>

				</select>
				<span class="input_span">商品价格区间：</span>
				<input type="text" name="minPrice" value="${params.minPrice}"/>
				- <input type="text" name="maxPrice" value="${params.maxPrice}"/>
				<input type="submit" value="查 询">
			</form>
		</div>
		
		<!-- 放置分页按钮 -->
		<div class="pagehelper">
			
		</div>
		
		<div id="prod_content">
		<c:forEach items="${prodList}" var="prod">
			<div class="prod_div">
				<a href="${pageContext.request.contextPath}/prodInfo?pid=${prod.id}" target="-blank">
				<img src="${pageContext.request.contextPath}${prod.imgurl}" border="0"></img>
				</a>
				<div id="prod_name_div">
					<a href="${pageContext.request.contextPath}/prodInfo?pid=${prod.id}" target="-blank">
						${prod.name}
					</a>
				</div>
				<div id="prod_price_div">
					￥${prod.price}元
				</div>
				<div>
					<div id="gotocart_div">
						<a href="${ pageContext.request.contextPath }/cart/addCart?pid=${prod.id}&buyNum=1">加入购物车</a>
					</div>					
					<div id="say_div">
						133人评价
					</div>					
				</div>
			</div>
			</c:forEach>
			<div style="clear: both"></div>
		</div>
	</div>
	<%@ include file="_foot.jsp" %>
	
	<script src="${pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
	<script src="${pageContext.request.contextPath }/js/pageHelper.js"></script>
	
	<script>
		$(function() {
			let API_URL = "${pageContext.request.contextPath}/prodlist";
			let curPage = ${page};  
			let total = ${total};
			let count = ${count}; 
			let sideBtnCount = 2;
			let urlParamsStr = '${urlParamsStr}';
			
			let btnHtml = pageHelper(API_URL, curPage, total, count, sideBtnCount, urlParamsStr);
			$('div.pagehelper').html(btnHtml);
		})
	</script>
</body>
</html>
