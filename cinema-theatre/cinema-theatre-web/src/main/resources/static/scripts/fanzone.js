/**
 * @author Nikola Stojanovic
 * 
 */

function loadPropses() {
	$.get("../fan-zone", function(data) {
		if (data != null) {
			console.log(data.length);
			
			for(i = 0; i < data.length; i++){
				
				var desc = $("<p/>", {
					html: data[i].propsDesc
				});
				
				var img = $("<img/>", {
					width: 80,
					height: 80
				});
				
				if(data[0].propsImage != null){
					i.attr("src", "data:application/unknown;base64, " + data.content;);
				}else{
					i.attr("src", ???);
				}
				
			}
			
		} else {
			$("#ads-container")[0].innerHTML = "There are no ads right now.";
		}
	});
}