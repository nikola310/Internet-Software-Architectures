/**
 * @author Zivko Stanisic
 */
var id;
$(document).ready(function() {
	id = getUrlParameter('id');
	$.get("cinema-theatre/" + id, function(data) {

		$("#ctName").text(data.ctName);
		$("#ctStateid").text(data.ctStateid);
		$("#ctAdress").text(data.ctAdress);
		$("#ctDescription").text(data.ctDescription);
		$("#ctPhone").text(data.ctPhone);
		
		$('#ctTable').append('<tr><td><a href="halls.html?id=' + id +'">See repertoire.</a></td></tr>');

	});
});
