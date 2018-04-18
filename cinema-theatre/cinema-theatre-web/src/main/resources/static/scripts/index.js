/**
 * @author Zivko Stanisic
 */
var id;
$(document).ready(
		function() {
			$.get("friends-list/friends", function(data) {
				if (data.length > 0) {
					for (i = 0; i < data.length; i++) {
						var link;
						if (data[i].status == "P") {
							link = '<a onclick="accept('+ data[i].id +')">Accept.<a> <a onclick="refuse('+ data[i].id +')">Refuse.<a></td></tr>';
						} else if (data[i].status == "A") {
							link = '<a onclick="remove('+ data[i].id +')">Remove.<a></td></tr>';
						}
						$('#friendsTable').append(
								'<tr><td>' + data[i].name + ' '
										+ data[i].surname + ' '
										+ link);
					}
				}

			});
		});

function accept(id) {
	$.get("friends-list/accept/" + id, function(data) {
		window.location.replace("index.html");
	});
}

function refuse(id) {
	$.get("friends-list/refuse/" + id, function(data) {
		window.location.replace("index.html");

	});
}

function remove(id) {
	$.get("friends-list/remove/" + id, function(data) {
		window.location.replace("index.html");

	});
}