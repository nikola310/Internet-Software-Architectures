/**
 * @author Nikola Stojanovic
 */

function getCurrentBid(){
	$.get("bid/current", function(data){
		console.log("current: " + data);
	});
}