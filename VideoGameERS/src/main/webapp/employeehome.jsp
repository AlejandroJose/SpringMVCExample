<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.beans.ExpenseReimbursement"%>
<%@ page import="com.revature.beans.User"%>
<%@ include file="/resources/tiles/jstl.jsp" %>
<jsp:include page="/employee" />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/resources/tiles/header/header.jsp"%>
<title>ERS Home Pages</title>
<script src="resources/js/homepage.js"></script>
<!--<link href="resources/css/core.css" type="text/css" rel="stylesheet"/>-->
</head>
<body style="background-color: #e0e0e0;">
	<jsp:include page="/resources/tiles/navbars/usernavbar.jsp"></jsp:include>
	<%!User logedInUser = null;%>

	<%
		logedInUser = (User) session.getAttribute("user");
	%>
	<div class="container test">
		<h1>Welcome to the Employee Home Page: ${sessionScope.user.getUsername()}</h1>
		<div class="row">
			<div class="col-md-10">
				<div class="panel panel-primary">
					<div class="panel-heading">
						<h3 class="panel-title">Reimbursement Requests</h3>
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
								<th>#</th>
								<th>Amount</th>
								<th>Description</th>
								<th>Receipt</th>
								<th>Date Submitted</th>
								<th>Author</th>
								<th>Date Resolved</th>
								<th>Resolved by</th>
								<th>Type</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${erRequests}" var = "expReim">
								<tr>
									<td>${expReim.getId()}</td>
									<td><fmt:formatNumber value="${expReim.getAmount()}" type="currency" /></td>
									<td>${expReim.getDescription()}</td>
									<td><img alt="img" class="r_images" src="data:image/jpg;base64,${expReim.getBase64Image()}" style="width:100px;height:100px;"/></td>
									<td>${expReim.getSubmitted()}</td>
									<td>${expReim.getAuthorId()}</td>
									<td>${expReim.getResolved()}</td>
									<td>${expReim.getResolverUsername()}</td>
									<td>${expReim.getErType()}</td>
									<td>${expReim.getErStatus()}</td>
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