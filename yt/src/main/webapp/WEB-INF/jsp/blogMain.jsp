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
<h1>List of Videos</h1>
Current estate is ${estateId}<br>
${reportMsg}
<br>

<c:if test="${not empty videoToPlayUrl}">
<div class="video playback">
<object width="425" height="355">
  <param name="movie" value="${videoToPlayUrl}"></param>
  <param name="allowFullScreen" value="true"></param>
  <embed src="${videoToPlayUrl}"
    type="application/x-shockwave-flash"
    width="425" height="355" 
    allowfullscreen="true">
  </embed>
</object>
</div>

</c:if>



<table>
	<tr class="tableHeader">
   		<td width="30">Id</td>
   		<td width="80">Video</td>
   		<td width="120">Thumbnail</td>
   		<td width="300">webPlayerUrl</td>
   		<td width="300">embeddedWebPlayerUrl</td>
	</tr>
	<% int i = 0; %>
	<c:if test="${not empty videos}">
	<c:forEach items="${videos}" var="video">
	<tr  <% if(i%2==0){ %>class="tableColorRow" <%} %> >
		<td>${video.ytVideoId}</td>
   		<td>${video.title}</td>
   		<td>
   			<a href="<c:url value="/blogMain.do?estateId=${estateId}&ytVideoId=${video.ytVideoId}"/>"><img src="${video.defaultetThumbnail}"></a>
   		</td>
   		<td>${video.webPlayerUrl}</td>
   		<td>${video.embeddedWebPlayerUrl}</td>
	</tr>		
	<% i++; %>
	</c:forEach>
	</c:if>
</table>
		 
</body>

</html>

