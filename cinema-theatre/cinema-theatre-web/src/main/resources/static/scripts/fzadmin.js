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
	FR.readAsDataURL($("#inputImage")[0].files[0]);
}

function loadNotCheckedOffers() {
	$.get("props/not-checked",
			function(data) {
				var t = $("#table-check")[0];
				if (data.length > 0) {

					for (i = 0; i < data.length; i++) {

						var row = t.insertRow(1);
						var cell1 = row.insertCell(0);
						var cell2 = row.insertCell(1);
						var cell3 = row.insertCell(2);
						var cell4 = row.insertCell(3);
						var cell5 = row.insertCell(4);
						

						cell1.innerHTML = data[i].propsName;

						cell2.innerHTML = data[i].propsDesc;
						var img = $("<img/>");
						if (data[i].propsImage != null) {
							img.attr("src", "data:application/unknown;base64, "
									+ data[i].propsImage);
							img.attr("alt", data[i].propsDesc);
						} else {
							img.attr("src", "images/no-img.png");
							img.attr("alt", "No image.")
						}
						cell3.innerHTML = img[0].outerHTML;

						cell4.innerHTML = new Date(data[i].propsDeadline)
								.toUTCString();

						var accept = $("<button/>", {
							id : "props-accept-" + data[i].propsId,
							onclick : "acceptOffer(this)"
						});
						accept[0].innerHTML = " Accept ";

						var reject = $("<button/>", {
							id : "props-reject-" + data[i].propsId,
							onclick : "rejectOffer(this)"
						});
						reject[0].innerHTML = " Reject ";

						cell5.innerHTML = accept[0].outerHTML
								+ reject[0].outerHTML;
					}
				} else {

				}
			});
}

function acceptOffer(e) {
	console.log(e.id);
	var propsID = e.id.split(/-+/)[2];

	$.ajax({
		type : "PUT",
		url : "props/approve/" + propsID,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			$("#table-check").find("tr:gt(0)").remove();
			loadNotCheckedOffers();
		},
		fail : function(data) {
			console.log(data);
		},
		error : function(data) {
			console.log(data);
		}
	});
}

function rejectOffer(e) {
	var propsID = e.id.split(/-+/)[2];

	$.ajax({
		type : "PUT",
		url : "props/reject/" + propsID,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			$("#table-check").find("tr:gt(0)").remove();
			loadNotCheckedOffers();
		},
		fail : function(data) {
			console.log(data);
		},
		error : function(data) {
			console.log(data);
		}
	});
}

function add_official() {

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
		url : "props/official",
		data : dt,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			alert("Prop succesfully registered");
			// window.location = "fanzone.html";
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

function official_props() {
	$.get("props/official", function(data) {
		if (data.length > 0) {

			for (i = 0; i < data.length; i++) {

				var kol1 = $("<td/>", {
					html : data[i].propsName
				});

				var kol2 = $("<td/>", {
					html : data[i].propsDesc
				});

				var img = $("<img/>");
				if (data[i].propsImage != null) {
					img.attr("src", "data:application/unknown;base64, "
							+ data[i].propsImage);
				} else {
					img.attr("src", "");
				}

				var kol3 = $("<td/>", {
					html : img[0].outerHTML
				});

				var kol4 = $("<td/>", {
					html : new Date(data[i].propsDeadline).toUTCString()
				});

				var edit = $("<button/>", {
					id: "props-" + data[i].propsId,
					html: "Edit"
				});
				edit.attr("onclick", "edit_official(this)");
				
				var del = $("<button/>", {
					id: "props-delete-" + data[i].propsId,
					html: "Delete"
				});
				del.attr("onclick", "delete_official(this)");
				
				var kol5 = $("<td/>", {
					html : edit[0].outerHTML + del[0].outerHTML
				});

				var red = $("<tr/>", {
					html : kol1[0].outerHTML + kol2[0].outerHTML
							+ kol3[0].outerHTML + kol4[0].outerHTML
							+ kol5[0].outerHTML
				});

				$('#check-official tbody').append(red[0].outerHTML);

			}
		}
	});
}

function delete_official(e){
	var propsID = e.id.split(/-+/)[2];
	
	$.ajax({
		type : "DELETE",
		url : "props/" + propsID,
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			$("#props-delete-" + propsID).parent().parent()[0].remove();
			alert("Prop succesfully deleted");
		},
		fail : function(data) {
			console.log(data);
		},
		error: function(data){
			console.log(data);
		}
	});
}