<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
	<head>
	<title>修改商品</title>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<style type="text/css">
			.updateprod{
				padding: 20px 30px;
				font-size: 22px;
				color: #686868;
			}
			.prod_id::-ms-clear{
				display:none;
			}
		</style>
	</head>
	<body>	
		<%@ include file = "adminhead.jsp" %>
		<br/>
		<div id="index" style="font-size: 20px;text-align:center;">
			修改商品
		</div>
		<br/>
		<div style="font-size: 15px;text-align:center;">
			<a href="${ pageContext.request.contextPath }/admin/showprod">返回</a>
		</div>
	<div class="updateprod">
	<jsp:useBean id="myproducts" class="easymall.pojo.MyProducts3" scope="request"/>
	<form:form modelAttribute="myproducts" onsubmit="return formobj.checkForm();" method="POST"
	   enctype="multipart/form-data" action="${ pageContext.request.contextPath }/admin/update">			
		<fieldset>
		<legend>修改一件商品</legend>	
		<p>
			<label>商品id</label>
			<form:input style="width:250px;" class="prod_id" path="id" value="${product.id}" readonly="readonly"  unselectable="on"/>
		</p>		
		<p>
            <label>商品名:</label>
            <form:input path="name" value="${product.name}"/>
        </p><p>
            <label>商品价格:</label>
            <form:input path="price" value="${product.price}"/>
        </p><p>
            <label>商品类别:</label>
            <form:select path="category">
            <!-- 通过循环语句将所有商品类别显示在下拉列表中 -->
			<c:forEach items="${categorys}" var="c">
				<c:if test="${product.category==c.name}">
					<option value="${c.id}" selected="selected">${c.name}</option>
				</c:if>
				<c:if test="${product.category!=c.name}">
					<option value="${c.id}">${c.name}</option>
				</c:if>
			</c:forEach>
			</form:select>
        </p><p>
            <label>库存:</label>
            <form:input path="pnum" value="${product.pnum}"/>
        </p><p>
            <label>图片:</label>
            <input type="file" name="imgurl" value="${product.imgurl}"/>
        </p><p>
            <label>商品描述:</label>
            <form:input style="width:250px;" path="description" value="${product.description}"/>
        </p><p id="buttons">
            <input id="reset" type="reset">
            <input id="submit" type="submit" value="修改">
        </p>
    	</fieldset>
    	<!-- 取出所有验证错误 -->
    	<form:errors path="*"/>
	</form:form>
	</div>
	<%@ include file = "_foot.jsp" %>
	</body>
</html>s