/**
 * @author Zivko Stanisic
 */
$(document).ready(
		function() {
			var token = getUrlParameter('token');

			$.get("http://localhost:8080/cinema-theatre/user/confirmation/"
					+ token, function(data, textStatus, jqXHR) {
						if (textStatus == "success") {
							$("#confirmationText").text("Registration finished successfully!");
						} else {
							$("#confirmationText").text("Wrong confirmation link or expiration time elapsed!");
						}				
			});
		});