/**
 * @author Nikola Stojanovic
 */

function loadNotCheckedOffers() {
	$.get("props/not-checked", function(data) {
		var t = $("#table-check")[0];
		if(data.length > 0){
			
			for (i = 0; i < data.length; i++) {
	
				var row = t.insertRow(1);
				var cell1 = row.insertCell(0);
				var cell2 = row.insertCell(1);
				var cell3 = row.insertCell(2);
				var cell4 = row.insertCell(3);
				var cell5 = row.insertCell(4);
				var cell6 = row.insertCell(5);
	
				cell1.innerHTML = data[i].propsName;
	
				cell2.innerHTML = data[i].propsDesc;
				var img = $("<img/>");
				if (data[i].propsImage != null) {
					img.attr("src", "data:application/unknown;base64, "
							+ window.atob(data[i].propsImage));
				} else {
					img.attr("src", "");
				}
				cell3.innerHTML = img.outerHTML;
	
				cell4.innerHTML = new Date(data[i].propsDeadline).toUTCString();
	
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
	
				if (data[i].propsUsed == false) {
					cell5.innerHTML = "Yes";
				} else {
					cell5.innerHTML = "No";
				}
	
				cell6.innerHTML = accept[0].outerHTML + reject[0].outerHTML;
			}
		}else{
			
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