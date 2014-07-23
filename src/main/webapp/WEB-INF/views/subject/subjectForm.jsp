<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
	<title>产品管理</title>
</head>

<body>
	<form id="inputForm" action="${ctx}/subject/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="id" value="${subject.id}"/>
		<fieldset>
			<legend><small>管理产品</small></legend>
			<div class="control-group">
				<label for="subject_title" class="control-label">产品名称:</label>
				<div class="controls">
					<input type="text" id="subject_name" name="name"  value="${subject.name}" class="input-large required" minlength="3"/>
				</div>
			</div>	
			<div class="control-group">
				<label for="description" class="control-label">产品描述:</label>
				<div class="controls">
					<textarea id="description" name="description" class="input-large">${subject.description}</textarea>
				</div>
			</div>	
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
			</div>
		</fieldset>
	</form>
	<script>
		$(document).ready(function() {
			//聚焦第一个输入框
			$("#subject_name").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
		});
	</script>
</body>
</html>
