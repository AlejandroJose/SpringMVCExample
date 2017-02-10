<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ include file="/resources/tiles/jstl.jsp"%>
<jsp:include page="/newRequest" />
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Reimbursement Request</title>
<%@ include file="/resources/tiles/header/header.jsp"%>
</head>
<body>
	<jsp:include page="/resources/tiles/navbars/usernavbar.jsp"></jsp:include>
	<div class="well bootstrap-iso">
		<div id="myTabContent" class="tab-content container-fluid">
			<h1 class="page-header">New Reimbursement Request</h1>
			<div class="row tab-pane active in" id="home">
				<div class="col-md-8 col-sm-6 col-xs-12 personal-info">
					<form class="form-horizontal" method="POST" action="newrequest.jsp" enctype="multipart/form-data">
						<div class="form-group">
							<label class="col-lg-3 control-label">Amount:</label>
							<div class="col-lg-8">
								<input class="form-control" value="" name="amount" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Description:</label>
							<div class="col-lg-8">
								<textarea class="form-control" rows="3" name="description"></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Upload a Receipt:</label>
							<div class="col-lg-8">
								<input type="file" name="photo" size="50" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Type of Request:</label>
							<div class="col-lg-8">
								<select class="select form-control" id="select" name="type">
							       <option value="1">
							        Travel
							       </option>
							       <option value="2">
							        Food
							       </option>
							       <option value="3">
							        Housing
							       </option>
							    </select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-8">
								<button class="btn btn-primary">Submit</button>
								<span></span> <input class="btn btn-default" value="Cancel"
									type="reset">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	    $(document).ready(function(){
	      var date_input=$('input[name="date"]'); //our date input has the name "date"
	      var container=$('.bootstrap-iso form').length>0 ? $('.bootstrap-iso form').parent() : "body";
	      var options={
	        format: 'dd/mm/yyyy',
	        container: container,
	        todayHighlight: true,
	        autoclose: true,
	      };
	      date_input.datepicker(options);
	    })
	</script>
</body>
</html>