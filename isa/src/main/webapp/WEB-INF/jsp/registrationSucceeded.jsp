<jsp:include page="header.jsp" />

<% request.getSession().setAttribute("currentPage", "registrationSucceeded.jsp"); %>
<h3>Registration succeeded!</h3>
<h2>Return to home page <a href="index.jsp">here</a>.</h2>


<jsp:include page="footer.jsp" />