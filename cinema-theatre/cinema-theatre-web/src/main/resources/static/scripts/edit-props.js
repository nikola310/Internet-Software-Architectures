/**
 * @author Nikola Stojanovic
 */

// Za ucitavanje slike
var glob;

// Pretvara sliku u base64
function loadImage() {
	var FR = new FileReader();
	FR.addEventListener("load", function(e) {
		glob = e.target.result;
	});
	FR.readAsDataURL($("#edit-image")[0].files[0]);
}

function read_props() {

	$.get("props/current", function(data) {
		console.log(data)
		if (data != null) {
			$("#edit-name").val(data.propsName);
			$("#edit-desc").val(data.propsDesc);
			$("#edit-date").val(
					new Date(data.propsDeadline).toISOString().slice(0, -1));
			if (data.propsImage != null) {
				$("#image-preview").attr("src",
						"data:application/unknown;base64, " + data.propsImage);
				$("#image-preview").attr("alt", data.propsDesc);
			} else {
				$("#image-preview").attr("src", "images/no-img.png");
				$("#image-preview").attr("alt", "No image.");
			}

			$("#edit-price").val(data.propsPrice);
			$("#edit-btn").attr("props-id", data.propsId);
			$("#edit-btn").attr("user-id", data.userId);
			$("#edit-btn").attr("props-used", data.propsUsed);
			$("#edit-btn").attr("props-approved", data.propsApproved);
		}
	});

	return false;
}

function edit_props() {
	var propsID = $("#edit-btn").attr("props-id");
	var userID = $("#edit-btn").attr("user-id");
	
	var used;
	if($("#edit-btn").attr("props-used") == "true"){
		used = true;
	}else{
		used = false;
	}
	
	var approved;
	if($("#props-approved").attr("props-used") == "true"){
		approved = true;
	}else if($("#props-approved").attr("props-used") == "false"){
		approved = false;
	}else{
		approved = null;
	}
	
	if (glob != null) {
		glob = glob.split(/,(.+)/)[1];
	} else {
		glob = $("#image-preview").attr("src").split(/,(.+)/)[1].substring(1);
	}

	var dt = JSON.stringify({
		"propsId" : parseInt(propsID),
		"propsName" : $("#edit-name").val(),
		"propsDesc" : $("#edit-desc").val(),
		"propsDeadline" : new Date($("#edit-date").val()).getTime(),
		"propsImage" : glob,
		"userId" : userID,
		"propsUsed" : used,
		"propsPrice" : parseFloat($("#edit-price").val()),
		"propsApproved" : null
	});

	$.ajax({
		type : "PUT",
		url : "props",
		data : dt,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log(data);
			alert("Prop succesfully modified");
			window.location.replace("fzadmin.html");
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