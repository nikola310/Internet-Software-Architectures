<jsp:include page="header.jsp" />

<% request.getSession().setAttribute("currentPage", "profileImage.jsp"); %>

<form action="/Forum/ProfileImage" name="uploadPicture" enctype = "multipart/form-data" method="post">
	<table id="registrationTable" class="registrationTable">
		<tr>
		
			<td>Select a image to upload:</td>
			<td><input type = "file" id="file" name = "file" accept="image/*"/></td>
		
		</tr>

		<tr>
			<td><input type="submit" value="Upload picture" name="Confirm"
				class="button"></td>
		</tr>

	</table>
	<label style="color: red;" id="imageError">${imageError}</label>
		<% getServletContext().setAttribute("imageError", ""); %>

</form>
<jsp:include page="footer.jsp" />