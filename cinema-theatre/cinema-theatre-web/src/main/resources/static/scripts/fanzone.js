/**
 * @author Nikola Stojanovic
 * 
 */

function loadAds() {
	$.get("../fan-zone", function(data) {
		if (data != null) {
			console.log($(data).length)
		} else {
			$("#ads-container")[0].innerHTML = "There are no ads right now";
		}
	});
}