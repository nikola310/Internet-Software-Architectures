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

	if(glob != null)
		glob = glob.split(/,(.+)/)[1];
	
	var used;
	if($("input[name=usedGroup]:checked").val() == "TRUE"){
		used = true;
	}else if($("input[name=usedGroup]:checked").val() == "FALSE"){
		used = false;
	}
	
	var dt = JSON.stringify({
		"propsName" : $("#props-name").val(),
		"propsDesc" : $("#props-desc").val(),
		"propsDeadline" : new Date($("#props-date").val()).getTime(),
		"propsImage" : glob,
		"userId" : 1,
		"propsUsed" : used,
		"propsPrice" : parseFloat($("#props-price").val()),
		"propsApproved" : null
	});
	
	$.ajax({
		type : "POST",
		url : "props",
		data : dt,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			alert("Prop succesfully registered");
			window.location = "fanzone.html";
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

function loadBids() {
	var offers = $("#offers-container");
	$.get("bid", function(data) {

		if (data.length > 0) {

			for (i = 0; i < data.length; i++) {

				/*var btn = "";
				if (!data[i].bidAccepted)
					btn = '<br/><input type="button" value="Accept">'

				var paragraf = $("<p/>", {
					html : "Props:" + data[i].bid + "<br/>Price: "
							+ data[i].bidPrice + btn
				});

				var div = $("<div/>", {
					id : "bid-" + i,
					html : paragraf[0].outerHTML
				});
				div.attr("class", "bid-div");

				offers.append(div[0].outerHTML);*/
				
				if(data[i].bidAccepted == null){
				
				var forma = $("<form/>", {
					
				});
				
					var btn = $("<input/>", {
						type: "submit",
						value: "Accept"
					})
				}
			}

		}

		if (offers[0].innerHTML == "")
			offers[0].innerHTML = "<p>There are no bids yet.</p>";

	});
}