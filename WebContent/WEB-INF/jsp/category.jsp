<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	<script rel="stylesheet" href="${pageContext.request.contextPath}/css/category.css"/>
	<script rel="stylesheet" href="${pageContext.request.contextPath}/js/echarts.js"/>
	<title>商品种类添加</title>
	<script type="text/javascript" src="${ pageContext.request.contextPath }/js/jquery-1.4.2.js"></script>
	<script>
		function deleteC(){
			var id=form1.id.value;
			window.location="${ pageContext.request.contextPath }/admin/category/deletecategory?id="+id;
		}
		
	</script>
	
</head>
<body>
	<!-- 将头部包含进来 -->
	<jsp:include page="/WEB-INF/jsp/adminhead.jsp"/>
	<!-- 添加内容 -->
	<div>
	<table class="main" align="center">
		<tr id="title">
			<td>商品类别名</td>
			<td>修改类别名</td>
			<td>修改描述</td>
			<td></td>
		</tr>
		<tr>
		<form method="POST" action="${ pageContext.request.contextPath }/admin/category/updatecategory" name="form1">
			<td>
				<select name="id" id="id">
	            <!-- 通过循环语句将所有商品类别显示在下拉列表中 -->
					<c:forEach items="${categorys}" var="c">
						<option value="${c.id}">${c.name}</option>
					</c:forEach>
				</select>
			</td>
			<td>
				<input type="text" name="name"/>
			</td>
			<td>
				<input type="text" name="description"/>
			</td>
			<td id="buttons">
				<input type="submit" value="修改"/>
			</td>
		</form>
			<td>
				<button class="deleteC" value="删除" onclick="deleteC()">删除</button>
			</td>
		</tr>
		<tr>
		<form action="${ pageContext.request.contextPath }/admin/category/addcategory" method="POST">
			<td>添加：</td>
			<td>
				<input type="text" name="name" value="${category.name }"/>
			</td>
			<td>
				<input type="text" name="description" value="${category.description }"/>
			</td>
			<td>
				<input id="submit" type="submit" value="添加" class="addC"/>
			</td>
		</form>
		</tr>
		</table>
	</div>
	
</body>
</html>