<%@ page language="java" 
         contentType="text/html; charset=UTF-8" 
         pageEncoding="UTF-8"%>
<%@page import="no.dmma.yt.services.TestService"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
	<title>YT test application</title>
</head>
<body>
<h1>Hello</h1>
<p><%=TestService.get().sayHello()%></p>
<p><%=TestService.get().getEnvironment()%></p>

<div><a href="<c:url value="/adminMain.do"/>">Go to Admin</a></div>
<div><a href="<c:url value="/blogMain.do"/>">Go to Blog</a></div>
<div><a href="<c:url value="/allVideos.do"/>">View All user Videos</a></div>

<i>Dmitrijs Marcenkovs</i>
</body>
</html>