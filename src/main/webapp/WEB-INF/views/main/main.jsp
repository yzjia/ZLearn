<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>ZLearn</title>
<body>


	<div class="container">

		<div class="row row-offcanvas row-offcanvas-right">

			<div class="col-xs-12 col-sm-9">
				<p class="pull-right visible-xs">
					<button type="button" class="btn btn-primary btn-xs"
						data-toggle="offcanvas">Toggle nav</button>
				</p>
				<div class="jumbotron">
					<h1>内训平台</h1>
					<p>行业超前的内训平台帮助您快速培训员工</p>
					<shiro:notAuthenticated>
					<p>
						<a href="${ctx}/register" class="btn btn-primary btn-lg"
							role="button">马上加入</a>
					</p>
					</shiro:notAuthenticated>
					<shiro:authenticated>
					<p>欢迎使用CaseWOW</p>
					</shiro:authenticated>
				</div>
				<div class="row">
					<div class="col-6 col-sm-6 col-lg-4">
						<h2>灵活的测试系统</h2>
						<p>您可以为不同的员工制定不同的测试内容并进行测试跟踪。</p>
						<p>
							<a class="btn btn-default" href="#" role="button">更多 &raquo;</a>
						</p>
					</div>
					<!--/span-->
					<div class="col-6 col-sm-6 col-lg-4">
						<h2>丰富的培训资源</h2>
						<p>您的员工可以学习行平台中开放的培训资源，更快地进步。</p>
						<p>
							<a class="btn btn-default" href="#" role="button">更多 &raquo;</a>
						</p>
					</div>
					<!--/span-->
					<div class="col-6 col-sm-6 col-lg-4">
						<h2>标题</h2>
						<p>详细简介详细简介详细简介详细简介详细简介详细简介详细简介详细简介</p>
						<p>
							<a class="btn btn-default" href="#" role="button">更多 &raquo;</a>
						</p>
					</div>
					<!--/span-->
					<div class="col-6 col-sm-6 col-lg-4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce
							dapibus, tellus ac cursus commodo, tortor mauris condimentum
							nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
							malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn btn-default" href="#" role="button">更多 &raquo;</a>
						</p>
					</div>
					<!--/span-->
					<div class="col-6 col-sm-6 col-lg-4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce
							dapibus, tellus ac cursus commodo, tortor mauris condimentum
							nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
							malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn btn-default" href="#" role="button">更多 &raquo;</a>
						</p>
					</div>
					<!--/span-->
					<div class="col-6 col-sm-6 col-lg-4">
						<h2>Heading</h2>
						<p>Donec id elit non mi porta gravida at eget metus. Fusce
							dapibus, tellus ac cursus commodo, tortor mauris condimentum
							nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
							malesuada magna mollis euismod. Donec sed odio dui.</p>
						<p>
							<a class="btn btn-default" href="#" role="button">更多 &raquo;</a>
						</p>
					</div>
					<!--/span-->
				</div>
				<!--/row-->
			</div>
			<!--/span-->
			
			<shiro:notAuthenticated>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar"
				role="navigation">
				<form role="form" id="loginForm" action="${ctx}/login" method="post">
					<%
						String error = (String) request
								.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
						if (error != null) {
					%>
					<div class="alert alert-info" role="alert">
						<button class="close" data-dismiss="alert">×</button>
						登录失败，请重试.
					</div>
					<%
						}
					%>
					<div class="form-group">
						<label for="name">用户名</label> <input type="text"
							class="form-control input-medium required" id="username"
							name="username" value="${username}" placeholder="请输入用户名">
					</div>
					<div class="form-group">
						<label for="name">密码</label> <input type="password"
							class="form-control input-medium required" id="password"
							name="password" placeholder="请输入密码">
					</div>
					<div class="checkbox">
						<label> <input type="checkbox" id="rememberMe" name="rememberMe"> 记住我</input>
						</label>
					</div>
					<div class="control-group">
						<div class="controls">
							<input id="submit_btn" class="btn btn-primary" type="submit" value="登录" />
						</div>
					</div>
				</form>
			</div>
			</shiro:notAuthenticated>
			
			<shiro:authenticated>
			<div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar" role="navigation">
	          <div class="list-group">
	            <a href="#" class="list-group-item">链接1</a>
	            <a href="#" class="list-group-item">链接2</a>
	            <a href="#" class="list-group-item">链接3</a>
	            <a href="#" class="list-group-item">链接4</a>
	            <a href="#" class="list-group-item">链接5</a>
	            <a href="#" class="list-group-item">链接6</a>
	            <a href="#" class="list-group-item">链接7</a>
	            <a href="#" class="list-group-item">链接8</a>
	          </div>
	        </div>
			</shiro:authenticated>
		</div>

		<hr>
</body>
</html>

