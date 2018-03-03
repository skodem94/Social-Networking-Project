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
				<h1><a href="wall">OSN</a></h1>
				<div class="right">
					<p>
						Welcome <strong>${sessionScope.user.fullName}</strong> | 
						<a href="wall">My Wall</a> |
						<a href="settings">Settings</a> |
						<a href="logout?t=user">Logout</a>
					</p>
				</div>
			</div>
			
		</div>
	</div>
	<!-- End Header -->
	
    <div id="mainbody" class="shell"> 
           

   <jsp:include page="${sidebarFile}" flush="true" />
    
	<!-- Content -->
	<div id="content">