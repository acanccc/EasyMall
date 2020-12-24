<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="${ pageContext.request.contextPath }/css/index.css"/>
		<title>欢迎光临EasyMall</title>
	</head>
	<body>
	<!-- 将头部(_head.jsp)包含进来 -->
	<jsp:include page="/WEB-INF/jsp/adminhead.jsp"/>
		<div id="index" style="font-size: 20px;text-align:center;">
			后台管理主页
		</div>
	</body>
</html>
