/**
 * @author Nikola Stojanovic
 * 
 */

function addNewProps(){
	
}

function loadBids(){
	$.get("bid", function(data){
		console.log(data);
	});
}