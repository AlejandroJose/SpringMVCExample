<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:include page="/resources/tiles/jstl.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="/resources/tiles/header/header.jsp" %>
<title>Welcome</title>
</head>
<body style="background-color: #e0e0e0;">
	<jsp:include page="/resources/tiles/navbars/welcomenavbar.jsp"></jsp:include>
	
    <div class="container">

      <form class="form-signin col-md-4 col-md-offset-4" method="POST" action="login.jsp">
        <h2 class="form-signin-heading text-center">Sign In</h2>
        <label for="inputUsername" class="sr-only">Username</label>
        <input type="text" id="inputUsername" name="inputUsername" class="form-control" placeholder="Username" required autofocus>
        <label for="inputPassword" class="sr-only">Password</label>
        <input type="password" id="inputPassword" name="inputPassword" class="form-control" placeholder="Password" required>
        <div class="checkbox">
        </div>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
        <!--<a href="/login.jsp">Sign Up (coming soon)</a> -->
      </form>

    </div> <!-- /container -->


    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <script src="../../assets/js/ie10-viewport-bug-workaround.js"></script>
  </body>
</body>
</html>