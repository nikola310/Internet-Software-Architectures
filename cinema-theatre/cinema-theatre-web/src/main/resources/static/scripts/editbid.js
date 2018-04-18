/**
 * @author Nikola Stojanovic
 */

function getCurrentBid(){
	$.get("bid/current", function(data){
		$("#delete-btn").attr("bid", data.bidId);
		$("#bid-price").val(data.bidPrice);
	});
}

function edit_bid(){
	var price = parseInt($("#bid-price").val());
	$.ajax({
		type : "POST",
		url : "bid/set/price",
		data : { "price" : price },
		success : function(data) {
			console.log(data);
			alert("Bid succesfully modified");
		},
		fail : function(data) {
			console.log(data);
		},
		error: function(data){
			console.log(data);
		}
	});
	return false;
}

function delete_bid(e){
	var id = parseInt($(e).attr("bid"));
	$.ajax({
		type : "POST",
		url : "bid/delete",
		data : {"id":id},
		success : function(data) {
			console.log(data);
			alert("Bid succesfully deleted");
			window.location.replace("propses.html");
		},
		fail : function(data) {
			console.log(data);
		},
		error: function(data){
			console.log(data);
		}
	});
	return false;
}