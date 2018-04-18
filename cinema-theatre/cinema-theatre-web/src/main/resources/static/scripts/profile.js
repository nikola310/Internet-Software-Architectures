/**
 * @author Zivko Stanisic
 */
$(document).ready(function() {
	$.get("user/profile", function(data) {

		$("#userEmail").text(data.userEmail);
		$("#userName").text(data.userName);
		$("#userSurname").text(data.userSurname);
		$("#userPassword").text(data.userPassword);
		$("#userCity").text(data.userCity);
		$("#userStateid").text(data.userStateid);
		$("#userPhone").text(data.userPhone);

	});
});
