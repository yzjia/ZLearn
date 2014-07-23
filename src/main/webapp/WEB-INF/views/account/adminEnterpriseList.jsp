<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>企业管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr><th>登录名</th><th>企业名称</th><th>注册时间<th>管理</th></tr></thead>
		<tbody>
		<c:forEach items="${enterprises}" var="enterprise">
			<tr>
				<td><a href="${ctx}/admin/enterprise/update/${enterprise.id}">${enterprise.loginName}</a></td>
				<td>${enterprise.enterpriseName}</td>
				<td>
					<fmt:formatDate value="${enterprise.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
				</td>
				<td><a href="${ctx}/admin/enterprise/delete/${enterprise.id}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>
