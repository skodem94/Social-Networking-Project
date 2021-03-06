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
	<div id="content" style="border: none;min-height:400px;float: none;margin: 0 auto;">

<p id="pagehead" style="border: none;">Create User Account</p>

		<c:if test="${messages.success != null}">
		<div class="message success-message">
			<p><strong>${messages.success}</strong></p>
		</div>
		</c:if>

<c:if test="${statusMsg > 0}">
<div class="message success-message" style="width:auto">
	<p><strong>Registered Successfully !</strong></p>
</div> 
</c:if>

<form class="form form-aligned" action="register" method="post">
    <fieldset>
    <legend>Personal Details</legend>
        <div class="control-group">
            <label for="firstname">First Name</label>
            <input id="firstname" name="firstname" type="text" value="<c:out value="${param.firstname}"></c:out>"> <span class="red">${messages.firstname}</span>
        </div>
        <div class="control-group">
            <label for="lastname">Last Name</label>
            <input id="lastname" name="lastname" type="text" value="<c:out value="${param.lastname}"></c:out>"> <span class="red">${messages.lastname}</span>
        </div>
        
        <div class="control-group">      
      	<label for="lastname">Gender</label>
        <input id="radio-one" type="radio" name="gender" value="male" checked="checked">
       Male
        <input id="radio-two" type="radio" name="gender" value="female">
        Female
   		</div>

        <div class="control-group">
            <label for="dob">Date of Birth</label>
            <input id="dob" name="dob" type="text" value="<c:out value="${param.dob}"></c:out>"> <span class="red">${messages.dob}</span>
        </div>
        <div class="control-group">
            <label for="profession">Profession</label>
            <input id="profession" name="profession" type="text" value="<c:out value="${param.profession}"></c:out>" > <span class="red">${messages.profession}</span>
        </div>
        
        <div class="control-group">
            <label for="location">Location</label>
            <input id="location" name="location" type="text" value="<c:out value="${param.location}"></c:out>" > <span class="red">${messages.location}</span>
        </div>
            <legend>Contact Details</legend>
          <div class="control-group">
            <label for="email">Email Address</label>
            <input id="email" type="email" name="email" value="<c:out value="${param.email}"></c:out>"> <span class="red">${messages.email}</span>
        </div>
        
        <div class="control-group">
            <label for="mobileno">Mobile No.</label>
            <input id="mobileno" name="mobileno" type="text" value="<c:out value="${param.mobileno}"></c:out>" > <span class="red">${messages.mobileno}</span>
        </div>
            <legend>Account Details</legend>
		<div class="control-group">
            <label for="username">Username</label>
            <input id="username" name="username" type="text" value="<c:out value="${param.username}"></c:out>" > <span class="red">${messages.username}</span>
        </div>

        <div class="control-group">
            <label for="password">Password</label>
            <input id="password" type="password" name="password"> <span class="red">${messages.password}</span>
        </div>

        <div class="controls">
            <input type="submit" class="button button-primary" value="Submit">
            <input type="reset" class="button button-primary" value="Reset">            
        </div>
    </fieldset>
</form>

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