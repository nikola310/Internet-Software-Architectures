/**
 * @author Zivko Stanisic
 */
var id;
$(document).ready(
		function() {
			$.get("friends-list/peoples", function(data) {

				if (data.length > 0) {
					for (i = 0; i < data.length; i++) {
						$('#hallsTable').append(
								'<tr><td>' + data[i].name + ' '
										+ data[i].surname + ' '
										+ '<a href="otherProfile.html?id=' + data[i].id
										+ '">See profile.</td></tr>');
					}
				}

			});
		});