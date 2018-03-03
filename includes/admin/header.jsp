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
				<h1><a href="word?action=list">OSN</a></h1>
				<div class="right">
					<p>
						Welcome <strong>${sessionScope.adminUser.fullName}</strong> | 
						<a href="category?action=add">Add Category</a> |
						<a href="word?action=add">Add Word</a> |
						<a href="logout">Logout</a>
					</p>
				</div>
			</div>
			
		</div>
	</div>
	<!-- End Header -->
	
    <div id="mainbody" class="shell"> 
           
   <%@ include file="sidebar.jsp" %> 
    
	<!-- Content -->
	<div id="content">