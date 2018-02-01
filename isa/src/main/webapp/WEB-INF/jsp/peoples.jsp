<%@page import="java.util.ArrayList"%>
<%@ page import="beans.*"%>
<%@ page import="servlets.*"%>
<%@ page import="java.io.File"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<jsp:include page="header.jsp" />

<%
	request.getSession().setAttribute("currentPage", "peoples.jsp");
	pageContext.setAttribute("loggedUser", request.getSession().getAttribute("loggedUser"));
%>

<%
	Forum forum = (Forum) getServletContext().getAttribute("forum");
	ArrayList<User> users = forum.getUsers();
	pageContext.setAttribute("users", users);
%>
<div id="teamroster">
	<div id="teamroster_content">
		<table>
			<c:forEach var="user" items="${users}">
				<tr>
					<td><c:if test="${user.defaultImage == false}">		
        <p
								style="background-image: url('../data/images/default/profile.png'); background-size: 80px;">
								<a href="Profile?username=<c:out value="${user.userName}" />"><c:out
										value="${user.userName}" /></a>
							</p>
							<br class="clear" />
						</c:if> <c:if test="${user.defaultImage == true}">		
        <p
								style="background-image: url('../data/images/${user.userName}.jpg'); background-size: 80px;">
								<a href="Profile?username=<c:out value="${user.userName}" />"><c:out
										value="${user.userName}" /></a>
							</p>
							<br class="clear" />
						</c:if></td>

					<c:if test="${loggedUser != null}">
						<td style="width: 150px;"><a
							href="createMessage.jsp?to=${ user.userName }">Send Message.</a></td>
					</c:if>

					<c:if
						test="${(loggedUser != null) && (loggedUser.administrator == true) && (user.administrator == false)}">
						<td><a href="AddAdministrator?to=${ user.userName }">Give
								administrator position.</a></td>
					</c:if>

					<c:if
						test="${(loggedUser != null) && (loggedUser.userName != user.userName) && (user.administrator == true) && (loggedUser.administrator == true)}">
						<td><a href="RemoveAdministrator?to=${ user.userName }">Remove
								administrator position.</a></td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</div>
</div>



<jsp:include page="footer.jsp" />