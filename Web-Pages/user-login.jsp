<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-type" content="text/html; charset=utf-8" />
<title>OSN</title>
<link rel="stylesheet" href="css/style.css" type="text/css" media="all" />
<link rel="stylesheet" href="css/custom.css" type="text/css" media="all" />
</head>
<body>

	<!-- Header -->
	<div id="header">
		<div class="shell">
			
			<div id="head">
				<h1><a href="#">OSN</a></h1>
				<div class="right">
					<p>
						<a href="user-login.jsp">User Login</a> |
						<a href="admin-login.jsp">Admin Login</a>
					</p>
				</div>
			</div>
			
		</div>
	</div>
	<!-- End Header -->
	
    <div id="mainbody" class="shell"> 
           
    
	<!-- Content -->
	<div id="content" style="border: none;width:400px;min-height:400px;float: none;margin: 100px auto 0 auto;">

<p id="pagehead">USER LOGIN</p>

<c:if test="${statusMsg != null}">
<div class="message error-message" style="width:auto;height:auto">
	<p><strong>${statusMsg }</strong></p>
</div> 
</c:if>

      <form class="form form-aligned" method="post" action="userLoginAut">
    <fieldset>   

        
       <div class="control-group">
            <label for="username">User Name</label>
            <input id="username" name="username" type="text" value="">
        </div>
        
         <div class="control-group">
            <label for="userpass">Password</label>
            <input id="userpass" name="userpass" type="password" value="">
        </div>
      
        <div class="controls">
           
            <button type="submit" class="button button-primary">Login</button>

        </div>
    </fieldset>
</form>
<div align="right"><a href="register.jsp">Create new Account</a></div>


	</div>
	<!-- End Content -->
    
	</div>
    
<div style="clear: both;"></div>
<!-- Footer -->
<div id="footer">
	<p class="red">&copy;  <a href=""></a></p>
</div>
<!-- End Footer -->

</body>
</html>