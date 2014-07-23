<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<div id="header">
	<div class="navbar navbar-default navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand hidden-sm" href="${ctx}/">首页</a>
			</div>
			<div class="navbar-collapse collapse pull-right">
				<ul class="nav navbar-nav">
				<shiro:user>
					<li class="dropdown"><a href="javascript:void(0)"
						class="dropdown-toggle" data-toggle="dropdown"><shiro:principal property="name" />
						<b class="caret"></b></a>
						<ul class="dropdown-menu">
							<shiro:hasRole name="admin">
								<li><a href="${ctx}/admin/enterprise">管理企业用户</a></li>
								<li><a href="${ctx}/admin/user">管理用户</a></li>
							</shiro:hasRole>
							<shiro:hasRole name="enterprice">
								<li><a href="${ctx}/enterprise/user">管理用户</a></li>
							</shiro:hasRole>
							<li><a href="${ctx}/profile">修改个人信息</a></li>
							<li><a href="${ctx}/logout">注销</a></li>
						</ul></li>
				</shiro:user>
					<li class="dropdown"><a href="javascript:void(0)"
						class="dropdown-toggle" data-toggle="dropdown">帮助<b
							class="caret"></b></a>
						<ul class="dropdown-menu">
							<li><a href="#"
								target="_blank">起步</a></li>
							<li><a href="#" target="_blank">在线咨询</a>
							</li>
							<li><a href="#"
								target="_blank">文档</a></li>
						</ul></li>
					<li><a href="/about/">关于</a></li>
				</ul>
			</div>
		</div>
	</div>

</div>