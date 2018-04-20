/**
 * @author Nikola Stojanovic
 * 
 */

// Za ucitavanje slike
var glob;

// Pretvara sliku u base64
function loadImage() {
	var FR = new FileReader();
	FR.addEventListener("load", function(e) {
		glob = e.target.result;
	});
	FR.readAsDataURL($("#inputImage")[0].files[0]);
}

function addNewProps() {

	if (glob != null)
		glob = glob.split(/,(.+)/)[1];

	var dt = JSON.stringify({
		"propsName" : $("#props-name").val(),
		"propsDesc" : $("#props-desc").val(),
		"propsDeadline" : new Date($("#props-date").val()).getTime(),
		"propsImage" : glob,
		"propsPrice" : parseFloat($("#props-price").val()),
	});

	$.ajax({
		type : "POST",
		url : "props/used",
		data : dt,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			alert("Prop succesfully registered");
		},
		fail : function(data) {
			console.log(data);
		},
		error : function(data) {
			console.log(data);
		}
	});

	return false;
}

function loadBids() {
	$.get("bid/byuser", function(data) {

		if (data.length > 0) {
			for (i = 0; i < data.length; i++) {
				var kol1 = $("<td/>", {
					html : data[i].propsId
				});

				var kol2 = $("<td/>", {
					html : data[i].bidPrice
				});

				var edit = $("<button/>", {
					id : "edit-" + data[i].bidId,
					html : "Edit",
					class : "ui-btn ui-shadow ui-corner-all",
					onclick : "setBid(this)"
				});

				var kol3 = $("<td/>", {
					html : edit[0].outerHTML
				});

				var red = $("<tr/>", {
					html : kol1[0].outerHTML + kol2[0].outerHTML
							+ kol3[0].outerHTML
				});

				$('#offers-table tbody').append(red[0].outerHTML);
			}
		} else {
			var paragraf = $("<p/>", {
				html : "There are no bids yet"
			});

			var kol = $("<td/>", {
				html : paragraf[0].outerHTML
			});

			var red = $("<tr/>", {
				html : kol[0].outerHTML
			});

			$('#offers-table thead').remove();
			$('#offers-table tbody').append(red[0].outerHTML);
		}

	});
}

function loadOffers() {
	$.get("bid/offers", function(data) {
		if (data.length > 0) {
			for (i = 0; i < data.length; i++) {

				var kol1 = $("<td/>", {
					html : data[i].propsName
				});

				var kol2 = $("<td/>", {
					html : data[i].bidPrice
				});

				var accept = $("<button/>", {
					id : "accept-" + data[i].bidId,
					html : "Accept",
					onclick : "acceptOffer(this)"
				});

				var reject = $("<button/>", {
					id : "reject-" + data[i].propsId,
					html : "Reject",
					onclick : "rejectOffer(this)"
				});

				var kol3 = $("<td/>", {
					html : accept[0].outerHTML + reject[0].outerHTML
				});

				var red = $("<tr/>", {
					html : kol1[0].outerHTML + kol2[0].outerHTML
							+ kol3[0].outerHTML
				});

				$('#pick-offers-table tbody').append(red[0].outerHTML);
			}
		} else {
			var paragraf = $("<p/>", {
				html : "There are no bids yet"
			});

			var kol = $("<td/>", {
				html : paragraf[0].outerHTML
			});

			var red = $("<tr/>", {
				html : kol[0].outerHTML
			});

			$('#pick-offers-table thead').remove();
			$('#pick-offers-table tbody').append(red[0].outerHTML);
		}
	});
}

function loadBidsByUserId(id) {
	$.get("bid/not-accepted/" + id, function(data) {
		console.log("bid not accepted response: " + data);
	});
}

function setBid(e) {
	var bidID = e.id.split(/-(.+)/)[1];
	$.post("bid/set/" + bidID, function(data) {
		window.location.replace("editbid.html");
	});
}

function acceptOffer(e){
	var bidID = parseInt(e.id.split(/-+/)[1]);
	$.ajax({
		type : "POST",
		url : "bid/accept",
		data : { "bidId" : bidID },
		success : function(data) {
			alert("Bid rejected.");
		},
		fail : function(data) {
			console.log(data);
		},
		error : function(data) {
			console.log(data);
		}
	});
}

function rejectOffer(e){
	var bidID = parseInt(e.id.split(/-+/)[1]);
	$.ajax({
		type : "POST",
		url : "bid/reject",
		data : { "bidId" : bidID },
		success : function(data) {
			alert("Bid rejected.");
		},
		fail : function(data) {
			console.log(data);
		},
		error : function(data) {
			console.log(data);
		}
	});
}