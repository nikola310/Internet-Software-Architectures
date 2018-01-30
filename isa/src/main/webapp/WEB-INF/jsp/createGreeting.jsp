<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Create Greeting</title>
</head>
<body>
	<!-- u primeru se koristi Spring forma gde je prethodno ukljucen tag ciji ce prefiks biti form -->
	<div id="createGreeting">
		<c:url var="action" value="/greetings/create" />
		<!-- Spring forme omogucavaju navodjenje svih metoda kroz atribut method (ne samo post i get).
			modelAttribute atribut elementa form predstavlja objekat koji ce se kreirati
			od delova forme definisanih input elementima sa path atributima i taj objekat
			se prihvata kao parametar metode createGreeting -->
		<form:form id="formGreeting" action="${action}" method="post"
			modelAttribute="greeting">
			<fieldset>
				<form:hidden path="id" />
				<form:label path="text">Text </form:label>
				<form:input path="text" />
				<form:errors path="text" />
			</fieldset>
			<p>
				<button type="submit">Submit</button>
			</p>
		</form:form>
	</div>
</body>
</html>