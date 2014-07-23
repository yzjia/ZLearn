<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>试题管理</title>
</head>

<body>
	<div style="padding: 10px 200px 10px; width:700px">
		<form id="inputForm" action="${ctx}/question/${action}" method="post" role="form">
				<legend><small>管理试题</small></legend>
				<div class="form-group">
					<label for="loginName" class="control-label">试题题目:</label>
						<input type="text" id="question" name="question" value="${question.question}"
							class="form-control required" minlength="3" />
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">类型:</label>
						<select id="qtype" name="qtype" class="form-control required" value="${question.qtype }">
							<option>请选择</option>
							<option value="1">单项选择</option>
							<option value="2">多项选择</option>
							<option value="3">判断</option>
							<option value="4">填空</option>
							<option value="5">问答</option>
						</select>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">选项:</label>
						<input type="text" id="options" name="options" value="${question.options}"
							class="form-control required" />
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">答案:</label>
						<input type="text" id="answer" name="answer" value="${question.answer}"
							class="form-control required" />
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">难度:</label>
						<select id="level" name="level" class="form-control required" value="${question.level}">
							<option>请选择</option>
							<option value="1">简单</option>
							<option value="2">普通</option>
							<option value="3">较难</option>
						</select>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">产品:</label>
						<select id="subjectId" name="subjectId" class="form-control required" value="${question.subject.id}">
							<option>请选择</option>
							<c:forEach items="${subjectList}" var="subject">
							<option value="${subject.id }">${subject.name }</option>
							</c:forEach>
						</select>
				</div>
				<div class="form-actions">
					<input id="submit_btn" class="btn btn-primary" type="submit"
						value="提交" />&nbsp; <input id="cancel_btn" class="btn"
						type="button" value="返回" onclick="history.back()" />
				</div>
		</form>
	</div>
	
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#question").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
