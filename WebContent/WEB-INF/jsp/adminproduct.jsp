<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
	<title>添加商品</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<style type="text/css">
			.addprod{
				padding: 20px 30px;
				font-size: 22px;
				color: #686868;
			}
		</style>
	</head>
	<body>	
	<jsp:include page="/WEB-INF/jsp/adminhead.jsp"/>
		<div id="index" style="font-size: 20px;text-align:center;">
			商品管理主页
		</div>
	</body>
	<div class="addprod">
	<jsp:useBean id="myproducts" class="easymall.pojo.MyProducts" scope="request"/>
	<form:form modelAttribute="myproducts" onsubmit="return formobj.checkForm();" method="POST"
	   enctype="multipart/form-data" action="${ pageContext.request.contextPath }/admin/save">			
		<fieldset>
		<legend>添加一件商品</legend>			
		<p>
            <label>商品名:</label>
            <form:input path="name"/>
        </p><p>
            <label>商品价格:</label>
            <form:input path="price"/>
        </p><p>
            <label>商品类别:</label>
            <form:select path="category">
            <!-- 通过循环语句将所有商品类别显示在下拉列表中 -->
			<c:forEach items="${categorys}" var="c">
				<option value="${c.id}">${c.name}</option>
			</c:forEach>
			</form:select>
        </p><p>
            <label>库存:</label>
            <form:input path="pnum"/>
        </p><p>
            <label>图片:</label>
            <input type="file" name="imgurl"/>
        </p><p>
            <label>商品描述:</label>
            <form:input path=""/>
        </p><p id="buttons">
            <input id="reset" type="reset">
            <input id="submit" type="submit" value="添加">
        </p>
    	</fieldset>
    	<!-- 取出所有验证错误 -->
    	<form:errors path="*"/>
	</form:form>
	</div>
	</body>
</html>