<jsp:include page="header.jsp" />
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="beans.*"%>
<%@ page import="servlets.*"%>

<%
	String image = (String) session.getAttribute("image");
	Object parameter = request.getParameter("user");
	pageContext.setAttribute("parameter", parameter);
%>


<table id="registrationTable" class="registrationTable">
	<tr>
		<td>Profile picture:<br /> <img src="${image}"
			alt="Profile Image" height="100" width="100"> <br /> <c:if
				test="${parameter == null}">

				<a style="font-size: 15px;" href="profileImage.jsp">Change
					profile picture.</a>
			</c:if>
		</td>
	</tr>

	<tr>
		<td><c:if test="${parameter == null}">

				<a style="font-size: 15px;" href="Inbox">Inbox.</a>
			</c:if></td>
	</tr>

	<tr>
		<td>Username:</td>
		<td>${ user.userName }</td>
	</tr>

	<tr>
		<td>Name:</td>
		<td>${ user.name }</td>
	</tr>

	<tr>
		<td>Surname:</td>
		<td>${ user.surname }</td>

	</tr>

	<tr>
		<td>Mail:</td>
		<td>${ user.mail }</td>

	</tr>

	<tr>
		<td>Phone number:</td>
		<td>${ user.telephone }</td>
	</tr>

	<tr>
		<td>Date of registration:</td>
		<td>${ user.dateOfRegistration }</td>
	</tr>

	<tr>
		<td>Followed subforums:</td>
	</tr>

	<c:forEach var="item" items="${ user.folowedSubforums }">
		<tr>
			<td><a href="SubforumView?name=<c:out value="${ item.name }" />"><c:out
						value="${ item.name }" /></a></td>
		</tr>
	</c:forEach>

	<tr>
		<td>Saved topics:</td>
	</tr>

	<c:forEach var="item" items="${ user.savedTopics }">
		<tr>
			<td><a
				href="TopicView?name=<c:out value="${ item.title }" />&parent=<c:out value="${ item.subforum.name }" />"><c:out
						value="${ item.title }" /></a></td>
		</tr>
	</c:forEach>

	<tr>
		<td>Saved comments:</td>
	</tr>

	<c:forEach var="item" items="${ user.savedComments }">
		<tr>
			<td><a href="Replays?replay=<c:out value="${ item }"/>"><c:out
						value="${ item.content }" /></a></td>
		</tr>
	</c:forEach>

</table>

<jsp:include page="footer.jsp" />