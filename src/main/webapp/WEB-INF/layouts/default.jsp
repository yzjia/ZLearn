<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>ZLearn:<sitemesh:title/></title>
<meta name="description" content="ZLearn是一个专注在线职业培训的教育平台，致力于提高中国职业教育发展。">
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />

<link type="image/x-icon" href="${ctx}/static/images/favicon.ico" rel="shortcut icon">
<link href="${ctx}/static/bootstrap/3.2.0/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link href="${ctx}/static/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />

<style type="text/css">
	body { padding-top: 70px;padding-bottom: 70px; }
</style>

<script src="${ctx}/static/jquery/jquery-1.9.1.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script src="${ctx}/static/bootstrap/3.2.0/js/bootstrap.min.js" type="text/javascript"></script>

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
    <script src="/assets/js/html5shiv.min.js?v=a3395c24af"></script>
    <script src="/assets/js/respond.min.js?v=a3395c24af"></script>
  <![endif]-->

  <script>
    var _hmt = _hmt || [];
  </script>

<sitemesh:head/>
</head>

<body>
	<div class="container">
		<%@ include file="/WEB-INF/layouts/header.jsp"%>
		<div id="content">
			<sitemesh:body/>
		</div>
		<%@ include file="/WEB-INF/layouts/footer.jsp"%>
	</div>
</body>
</html>