<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.revature.beans.ExpenseReimbursement"%>
<%@ page import="com.revature.beans.User"%>
<%@ include file="/resources/tiles/jstl.jsp" %>
<jsp:include page="/manager" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/resources/tiles/header/header.jsp" %>
<title>Manager Home</title>
<script src="resources/js/homepage.js"></script>
</head>
<body style="background-color: #e0e0e0;">
<jsp:include page="/resources/tiles/navbars/managernavbar.jsp"></jsp:include>
	<%!User logedInUser = null;%>

	<%
		logedInUser = (User) session.getAttribute("user");
	%>
	<div class="container">
		<h1>Welcome to the Manager Home Page: ${sessionScope.user.getUsername()}</h1>
		<div class="row">
			<div class="col-md-15">
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
								<th>Approve Request</th>
								<th>Deny Request</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${erRequests}" var = "expReim" varStatus="loopCount">
								<tr class="ers_table_row">
									<td id="erId">${expReim.getId()}</td>
									<td id="erAmount${loopCount.count}"><fmt:formatNumber value="${expReim.getAmount()}" type="currency" /></td>
									<td id="erDescription${loopCount.count}">${expReim.getDescription()}</td>
									<td id="erImage${loopCount.count}"><img alt="img" class="r_images" src="data:image/jpg;base64,${expReim.getBase64Image()}" style="width:100px;height:100px;"/></td>
									<td id="erDateSub${loopCount.count}">${expReim.getSubmitted()}</td>
									<td id="erAuthor${loopCount.count}">${expReim.getAuthorId()}</td>
									<td id="erRes${loopCount.count}">${expReim.getResolved()}</td>
									<td id="erResName${loopCount.count}">${expReim.getResolverUsername()}</td>
									<td id="erType${loopCount.count}">${expReim.getErType()}</td>
									<td id="erStatus${loopCount.count}" class="test">${expReim.getErStatus()}</td>
									<td id="erApproveBtn${loopCount.count}"><button class="btn btn-lg btn-primary btn-block approveBtn" type="submit" class="denyBtn">Approve</button></td>
									<td id="erDenyBtn${loopCount.count}"><button class="btn btn-lg btn-block btn-danger denyBtn" type="submit" class="denyBtn">Deny</button></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					</div>
				</div>
			</div>
		</div>
	</div>
<script type="text/javascript">
	
	$(document).ready(function () {
        $(".approveBtn").click(function (){

        var ajax = new XMLHttpRequest();
            ajax.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200){
                	console.log("success 1");
                }
            };

        var data = $(this).parent().siblings("#erId").html() + ",1";
        ajax.open("POST","manager",true);
        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        console.log(data);
        ajax.send("erId=" + data);

    });
    });

	$(document).ready(function () {
        $(".denyBtn").click(function (){

        var ajax = new XMLHttpRequest();
            ajax.onreadystatechange = function () {
                if (this.readyState == 4 && this.status == 200){
                	console.log("success 0");
                }
            };

        var data = $(this).parent().siblings("#erId").html() + ",0";
        ajax.open("POST","manager",true);
        ajax.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        console.log(data);
        ajax.send("erId=" + data);
    });
    });
	
</script>
</body>
</html>