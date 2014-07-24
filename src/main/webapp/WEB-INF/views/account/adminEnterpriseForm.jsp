<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<html>
<head>
	<title>企业管理</title>
</head>

<body>
	<div style="padding: 10px 200px 10px; width:700px">
		<form id="inputForm" action="${ctx}／admin/enterprise/update" method="post" role="form">
				<legend>
					<small>企业管理</small>
				</legend>
				<div class="form-group">
					<label for="loginName" class="control-label">用户名:</label>
						<input type="text" id="loginName" name="loginName" value="${enterprise.loginName }"
							class="form-control required" minlength="3" />
				</div>
				<div class="form-group">
					<label for="plainPassword" class="control-label">密码:</label>
					
						<input type="password" id="plainPassword" name="plainPassword"
							class="form-control required" />
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">确认密码:</label>
					
						<input type="password" id="confirmPassword" name="confirmPassword"
							class="form-control required" equalTo="#plainPassword" />
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">邮箱地址:</label>
					
						<input type="text" id="email" name="email" value="${enterprise.email }"
							class="form-control required" />
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">公司名称:</label>
						<input type="text" id="enterpriseName" name="enterpriseName" value="${enterprise.enterpriseName }"
							class="form-control required" />
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">组织属性:</label>
					
						<select id="orgAttribute" name="orgAttribute" class="form-control required" value="${enterprise.orgAttribute }">
							<option>请选择</option>
							<option value="政府机构">政府机构</option>
							<option value="事业单位">事业单位</option>
							<option value="行业协会">行业协会</option>
							<option value="中央直属企业">中央直属企业</option>
							<option value="国有企业">国有企业</option>
							<option value="外商独资企业">外商独资企业</option>
							<option value="中外合资企业">中外合资企业</option>
							<option value="中外合作企业">中外合作企业</option>
							<option value="民营企业">民营企业</option>
							<option value="其他">其他</option>
						</select>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">公司地址:</label>
					
						<input type="text" id="address" name="address" value="${enterprise.address }"
							class="form-control required" />
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">公司规模:</label>
					
						<select id="scale" name="scale" class="form-control required" value="${enterprise.scale }">
							<option>请选择</option>
							<option value="50">0-50人</option>
							<option value="100">51-100人</option>
							<option value="200">101-200人</option>
							<option value="300">201-300人</option>
							<option value="500">301-500人</option>
							<option value="1000">501-1000人</option>
							<option value="5000">1001-5000人</option>
							<option value="5001">5001人以上</option>
						</select>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">业务覆盖:</label>
					
						<select id="businessCover" name="businessCover" class="form-control required" value="${enterprise.businessCover }">
							<option>请选择</option>
							<option value="district">地区性业务</option>
							<option value="national">全国性业务</option>
							<option value="global">跨国性业务</option>
							<option value="other">其他</option>
						</select>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">行业类型:</label>
					
						<select id="businessType" name="businessType" class="form-control required" value="${enterprise.businessType }">
							<option>请选择</option>
							<option value="医药制造">医药制造</option>
							<option value="医药流通">医药流通</option>
							<option value="行业协会">行业协会</option>
							<option value="其他">其他</option>
						</select>
				</div>
				<div class="form-group">
					<label for="confirmPassword" class="control-label">联系人:</label>
					
						<input type="text" id="contacts" name="contacts" value="${enterprise.contacts }"
							class="form-control required" />
				</div>
				
				<div class="form-group">
					<label for="confirmPassword" class="control-label">身份证号:</label>
					
						<input type="text" id="idNum" name="idNum" value="${enterprise.idNum }"
							class="form-control required" />
				</div>
				
				<div class="form-group">
					<label for="confirmPassword" class="control-label">联系人电话:</label>
					
						<input type="text" id="mobile" name="mobile" value="${enterprise.mobile }"
							class="form-control required" />
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
			$("#enterpriseName").focus();
			//为inputForm注册validate函数
			$("#inputForm").validate();
			
			$("#orgAttribute").val(${enterprise.orgAttribute });
			$("#scale").val(${enterprise.scale });
			$("#businessCover").val(${enterprise.businessCover });
			$("#businessType").val(${enterprise.businessType });
		});
	</script>
</body>
</html>
