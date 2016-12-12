<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<form:form id="frm" method="post" commandName="module" class="form-horizontal">
    <form:hidden path="id" /> 
	<div class="box-body">
		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">名称</label>

			<div class="col-sm-10">
				<form:input type="text" class="form-control" id="name" path="name" placeholder="名称"/>
			</div>
		</div>
	</div>
</form:form>