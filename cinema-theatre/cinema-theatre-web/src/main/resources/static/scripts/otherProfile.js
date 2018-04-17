/**
 * @author Zivko Stanisic
 */
var id;
$(document).ready(function() {
	id = getUrlParameter('id');
	$.get("friends-list/is-friend/" + id, function(data, textStatus, jqXHR) {
		if (textStatus == "success") {
			$("#status").val("Remove friend.");
		} else {
			$("#status").val("Add friend.");
		}				
	});

});
function addFriend() {
	$.get("friends-list/add-friend/" + id, function(data, textStatus, jqXHR) {
		if (textStatus == "success") {
			$("#status").val("Remove friend.");
		} else {
			$("#status").val("Add friend.");
		}				
	});
}