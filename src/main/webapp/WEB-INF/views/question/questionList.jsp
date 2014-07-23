<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>试题管理</title>
</head>

<body>
	<c:if test="${not empty message}">
		<div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
	</c:if>
	<div class="row">
		<div class="span4 offset7">
			<form class="form-search" action="#">
				<label>名称：</label> <input type="text" name="search_LIKE_title" class="input-medium" value="${param.search_LIKE_title}"> 
				<button type="submit" class="btn" id="search_btn">Search</button>
		    </form>
	    </div>
	    <tags:sort/>
	</div>
	
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th>试题题目</th>
		<th>类型</th>
		<th>选项</th>
		<th>答案</th>
		<th>难度</th>
		<th>产品</th>
		<th>管理</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${questions.content}" var="question">
			<tr>
				<td><a href="${ctx}/question/update/${question.id}">${question.question}</a></td>
				<td>${question.qtypeStr}</td>
				<td>${question.options}</td>
				<td>${question.answer}</td>
				<td>${question.levelStr}</td>
				<td>${question.subject.name}</td>
				<td><a href="${ctx}/question/delete/${question.id}">删除</a></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<tags:pagination page="${questions}" paginationSize="5"/>

	<div><a class="btn" href="${ctx}/question/create">创建试题</a></div>
</body>
</html>
