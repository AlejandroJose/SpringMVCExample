<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/resources/tiles/jstl.jsp" %>
<jsp:include page="/manageRequest" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Insert title here</title>
	<script src="resources/js/homepage.js"></script>
	<link rel="stylesheet" type="text/css" href="/resources/css/core.css">
</head>
<body>
	<div class="well">
		<div id="myTabContent" class="tab-content">
			<h1 class="page-header">Manage Request</h1>
			<div class="row tab-pane active in" id="home">
				<div class="col-md-8 col-sm-6 col-xs-12 personal-info">
					<h3>Account: ${sessionScope.user.getUsername()}</h3>
					<form class="form-horizontal" method="POST" action="profile.jsp">
						<div class="form-group">
							<label class="col-lg-3 control-label">First name:</label>
							<div class="col-lg-8">
								<input class="form-control"
									value="${sessionScope.user.getFirstName()}" name="firstname" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Last name:</label>
							<div class="col-lg-8">
								<input class="form-control"
									value="${sessionScope.user.getLastName()}" name="lastname" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Role:</label>
							<div class="col-lg-8">
								<label class="form-control" id="roleId" >${sessionScope.user.getRoleId()}</label>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Email:</label>
							<div class="col-lg-8">
								<input class="form-control"
									value="${sessionScope.user.getEmail()}" name="email" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-8">
								<button class="btn btn-primary">Update</button> <span></span>
								 <input class="btn btn-default" value="Cancel" type="reset">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>