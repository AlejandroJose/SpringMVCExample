<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.beans.User"%>
<%@ include file="/resources/tiles/jstl.jsp" %>
<jsp:include page="/viewEmployees" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/resources/tiles/header/header.jsp" %>
<title>View Employees</title>
<script src="resources/js/homepage.js"></script>
</head>
<body>
<jsp:include page="/resources/tiles/navbars/managernavbar.jsp"></jsp:include>
<div class="container">
		<div class="row">
			<div class="col-md-8">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Current Employees</h3>
						<div class="pull-right">
							<span class="clickable filter" data-toggle="tooltip"
								title="Toggle table filter" data-container="body">
							</span>
						</div>
					</div>
					<div class="table-responsive">
					<table class="table table-hover ers-table" id="dev-table">
						<thead>
							<tr>
								<th>Username</th>
								<th>First Name</th>
								<th>Last Name</th>
								<th>Email</th>
								<th>Role</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${ersEmployees}" var = "employee" varStatus="loopCount">
								<tr class="ers_table_row">
									<td id="empUsername">${employee.getUsername()}</td>
									<td id="empFirstName">${employee.getFirstName()}</td>
									<td id="empLastName">${employee.getLastName()}</td>
									<td id="empGetEmail">${employee.getEmail()}</td>
									<td id="empRole">${employee.getRoleName()}</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>