/**
 * @author Nikola Stojanovic
 * 
 */

// Za ucitavanje slike
var glob;

function loadImage() {
	var FR = new FileReader();
	FR.addEventListener("load", function(e) {
		glob = e.target.result;
	});
	FR.readAsDataURL($("#inputImage")[0].files[0]);
}

function addNewProps() {

	if(glob != "")
		glob = glob.split(/,(.+)/)[1];
	var proba = window.btoa(glob);
	var used;
	if($("input[name=usedGroup]:checked").val() == "TRUE"){
		used = true;
	}else if($("input[name=usedGroup]:checked").val() == "FALSE"){
		used = false;
	}
	var user = {"user":1};
	var dt = JSON.stringify({
		"propsName" : $("#props-name").val(),
		"propsDesc" : $("#props-desc").val(),
		"propsDeadline" : $("#props-date").val(),
		"propsImage" : proba,
		"user" : user,
		"propsUsed" : used,
		"propsPrice" : parseFloat($("#props-price").val()),
		"propsApproved" : null
	});
	console.log(dt);
	$.ajax({
		type : "POST",
		url : "props/real",
		data : dt,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log(data);
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

				var btn = "";
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

				offers.append(div[0].outerHTML);
			}

		}

		if (offers[0].innerHTML == "")
			offers[0].innerHTML = "<p>There are no bids yet.</p>";

	});
}

// Pretvara sliku u base64
function encodeImageFileAsURL() {

	var filesSelected = document.getElementById("inputImageToLoad").files;
	if (filesSelected.length > 0) {
		var fileToLoad = filesSelected[0];

		var fileReader = new FileReader();

		fileReader.onload = function(fileLoadedEvent) {
			var srcData = fileLoadedEvent.target.result; // <--- data: base64

			var newImage = document.createElement('img');
			newImage.src = srcData;

			document.getElementById("imgPreview").innerHTML = newImage.outerHTML;
		}
		fileReader.readAsDataURL(fileToLoad);
	}
}
