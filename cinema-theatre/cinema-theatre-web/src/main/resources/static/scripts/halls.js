/**
 * @author Zivko Stanisic
 */
var id;
$(document).ready(
		function() {
			id = getUrlParameter('id');
			$.get("cinema-theatre/halls/" + id, function(data) {

				if (data.length > 0) {
					for (i = 0; i < data.length; i++) {
						$('#hallsTable').append(
								'<tr><td>' + data[i].hall + ' '
										+ data[i].performance + ' '
										+ data[i].date + ' '
										+ '<a href="reservation.html?id=' + data[i].id
										+ '">Make reservation.</td></tr>');
					}
				}

			});
		});