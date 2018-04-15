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

	$.get("props/2", function(data) {
		console.log(data)
		if (data != null) {
			$("#edit-name").val(data.propsName);
			$("#edit-desc").val(data.propsDesc);
			$("#edit-date").val(
					new Date(data.propsDeadline).toISOString().slice(0, -1));
			$("#image-preview").attr("src",
					"data:application/unknown;base64, " + data.propsImage);
			$("#edit-price").val(data.propsPrice);
			$("#edit-btn").attr("props-id", data.propsId);
			$("#edit-btn").attr("user-id", data.userId);
		}
	});

	return false;
}

function edit_props() {
	var propsID = $("#edit-btn").attr("props-id");
	var userID = $("#edit-btn").attr("user-id");
	
	if(glob != null){
		glob = glob.split(/,(.+)/)[1];
	}else{
		glob = $("#image-preview").attr("src").split(/,(.+)/)[1].substring(1);
	}
	
	var dt = JSON.stringify({
		"propsId" : parseInt(propsID),
		"propsName" : $("#edit-name").val(),
		"propsDesc" : $("#edit-desc").val(),
		"propsDeadline" : new Date($("#edit-date").val()).getTime(),
		"propsImage" : glob,
		"userId" : userID,
		"propsUsed" : true,
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