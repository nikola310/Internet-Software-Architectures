/**
 * @author Zivko Stanisic
 */
var id;
$(document).ready(function() {
	id = getUrlParameter('id');
	$.get("friends-list/profile/" + id, function(data) {

		$("#userEmail").text(data.userEmail);
		$("#userName").text(data.userName);
		$("#userSurname").text(data.userSurname);
		$("#userPassword").text(data.userPassword);
		$("#userCity").text(data.userCity);
		$("#userStateid").text(data.userStateid);
		$("#userPhone").text(data.userPhone);

	});
	
	$.get("friends-list/is-friend/" + id, function(data, textStatus, jqXHR) {
		if (data == "true") {
			$("#status").text("");
		} else {
			$("#status").text("Add friend.");
		}				
	});

});
function addFriend() {
	$.get("friends-list/add-friend/" + id, function(data, textStatus, jqXHR) {
		if (data == "true") {
			$("#status").text("");
		} else {
			$("#status").text("Add friend.");
		}				
	});
}