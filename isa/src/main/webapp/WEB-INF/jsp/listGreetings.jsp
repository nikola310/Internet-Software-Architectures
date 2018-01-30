<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Greetings : View all</title>
</head>
<body>
	<div id="greetings">
		<table>
			<tr>
				<th>ID</th>
				<th>Text</th>
				<th></th>
			</tr>
			<c:forEach items="${greetings}"  var="greeting">
				<tr>	
					<td><c:out value="${greeting.id}"/></td>				
					<td><c:out value="${greeting.text}"/></td>
					<td><a href="<c:url value="/greetings/update/${greeting.id}"/>">Update</a></td>
					<td><a href="<c:url value="/greetings/delete/${greeting.id}"/>">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>	
	<div id="newGreeting">
		<a href="<c:url value="/greetings/new"/>">Create new greeting</a>
	</div>
</body>
</html>
