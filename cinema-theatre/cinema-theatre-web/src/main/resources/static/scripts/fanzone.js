/**
 * @author Nikola Stojanovic
 * 
 */

function loadPropses() {
	var official = $("#official-container");
	var ads = $("#ads-container");
	$.get("props", function(data) {
		if (data.length > 0) {
			
			for(i = 0; i < data.length; i++){
				
				var div = $("<div/>", {
					id: "props-" + data[i].propsId,
				});
				div.attr("class", "props-div");
				
				var desc = $("<p/>", {
					html: data[i].propsDesc
				});
				
				var naslov = $("<h2/>", {
					html: data[i].propsName
				});
				
				var img = $("<img/>");
				
				if(data[i].propsImage != null){
					img.attr("src", "data:application/unknown;base64, " + data[i].propsImage);
					img.attr("alt", data[i].propsDesc);
				}else{
					img.attr("src", "images/no-img.png");
					img.attr("alt", "No image.")
				}
				
				if(data[i].propsUsed){
					
					var txt = $("<input/>", {
						id: "bid-value-" + data[i].propsId,
						type: "text"
					});
					
					var btn = $("<input/>", {
						type: "submit",
						value: "Send bid"
					});
					
					var forma = $("<form/>", {
						id: "bid-" + data[i].propsId,
						onsubmit: "return createBid(this)",
						html: txt[0].outerHTML + btn[0].outerHTML
					});
					
					div[0].innerHTML = naslov[0].outerHTML + img[0].outerHTML + desc[0].outerHTML + forma[0].outerHTML;
					
					ads.append(div[0].outerHTML);
				}else{
					var buyBtn = $("<button/>", {
						html: "Make a reservation",
						id: "reservation-" + data[i].propsId,
						onclick: "make_reservation(this)"
					});
					
					div[0].innerHTML = naslov[0].outerHTML + img[0].outerHTML + desc[0].outerHTML + buyBtn[0].outerHTML;
					
					official.append(div[0].outerHTML);
				}
			}			
		}
		
		if(official[0].innerHTML == "")
			official[0].innerHTML = "<p>There are no official ads right now.</p>";
		if(ads[0].innerHTML == "")
			ads[0].innerHTML = "<p>There are no ads right now.</p>";
	});
}

function createBid(e){
	console.log(e);
	var propsID =  e.id.split(/-(.+)/)[1];
	
	dt = JSON.stringify({
		"bidPrice" : parseFloat($("#bid-value-" + propsID).val()),
		"userId" : 1,
		"propsId" : parseInt(propsID),
		"propsAccepted" : null
	});
	
	$.ajax({
		type : "POST",
		url : "bid",
		data : dt,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			alert("Bid succesfully created");
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

function make_reservation(e){
	var propsID =  e.id.split(/-(.+)/)[1];
	var dt = JSON.stringify({
		"propsId" : propsID
	});
	
	$.ajax({
		type : "POST",
		url : "reservation/add",
		data : dt,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			alert("Reservation succesfully created");
		},
		fail : function(data) {
			console.log(data);
		},
		error: function(data){
			console.log(data);
		}
	});
}