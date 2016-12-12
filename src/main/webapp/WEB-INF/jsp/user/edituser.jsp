<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form id="frm" method="post" commandName="user" class="form-horizontal">
    <form:hidden path="id" /> 
	<div class="box-body">
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">用户名</label>

			<div class="col-sm-10">
				<form:input type="text" class="form-control" id="username" path="username" placeholder="用户名"/>
			</div>
		</div>
	</div>
</form:form>