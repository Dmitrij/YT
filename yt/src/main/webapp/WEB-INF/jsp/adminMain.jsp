<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>YT video integration - Admin page</title>
	<link type="text/css" rel="stylesheet" href="<c:url value="/css/base.css"/>">
</head>
<body>
<a href="<c:url value="/index.jsp"/>">Back to index</a>
<h1>Admin View</h1>
Current estate is ${estateId}
<br>
${reportMsg}
<br>

<div class="tyUploadForm">
	<form action="${token.url}?nexturl=http://localhost:8080<c:url value="/adminMain.do?estateId=${estateId}"/>" method ="post" enctype="multipart/form-data">
		<input type="hidden" name="token"   value="${token.token}"/>
		
		<div><input type="text" name="title"/></div>
		<div><input type="file" name="file"/></div>
		<div><input type="submit" value="go" /></div>
	</form>
</div>




<table>
	<% int i = 0; %>
	<c:if test="${not empty videos}">
	<tr>
	
	<c:forEach items="${videos}" var="video">
		<% if( i%4==0 && i >  0 ){%><%="</tr><tr>"%><% } %>	
		<td>
			<a href="<c:url value="/blogMain.do?estateId=${estateId}&ytVideoId=${video.ytVideoId}"/>"><img src="${video.defaultetThumbnail}"></a>
   			<a href="<c:url value="/adminMain.do?estateId=${estateId}&ytVideoId=${video.ytVideoId}&action=delete"/>">Delete this</a>
   		</td>
   		<% i++; %>
	</c:forEach>
	
		
	</tr>
	</c:if>
</table>
		 
</body>

</html>

