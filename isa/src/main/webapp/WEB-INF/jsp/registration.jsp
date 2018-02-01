<jsp:include page="header.jsp" />


<form action="/Forum/Registration" name="registration" enctype = "multipart/form-data" method="post">
	<table id="registrationTable" class="registrationTable">
		<tr>
			<td>Name:</td>
			<td><input type="text" name="name" id="name" class="text">
			</td>
		</tr>

		<tr>
			<td>Surname:</td>
			<td><input type="text" name="surname" id="surname"
				class="text"></td>
		</tr>

		<tr>
			<td>Mail:</td>
			<td><input type="text" name="mail" id="mail" class="text">
			</td>
		</tr>

		<tr>
			<td>Phone number:</td>
			<td><input type="text" name="phoneNumber" id="phoneNumber"
				class="text"></td>
		</tr>
		<tr>
			<td>Username:</td>
			<td><input type="text" name="username" id="username"
				class="text"></td>
		</tr>

		<tr>
			<td>Password:</td>
			<td><input type="password" name="password" id="password"
				class="text"></td>
		</tr>

		<tr>
			<td>Password confirmation:</td>
			<td><input type="password" name="confirmPassword"
				id="confirmPassword" class="text"></td>
		</tr>

		<tr>
			<td><input type="submit" value="Create Account" name="Confirm"
				class="button"></td>
		</tr>

	</table>
	<label style="color: red;" id="registrationError">${registrationError}</label>
		<% getServletContext().setAttribute("registrationError", ""); %>

</form>
<jsp:include page="footer.jsp" />