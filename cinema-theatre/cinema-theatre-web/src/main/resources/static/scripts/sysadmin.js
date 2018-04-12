/**
 * @author Nikola Stojanovic
 */

function addNewCT() {

	var dt = JSON.stringify({
		"ctName" : $("#ct_name").val(),
		"ctStateid" : $("#ct_state").val(),
		"ctPhone" : parseInt($("#ct_phone").val()),
		"ctAdress" : $("#ct_adress").val(),
		"ctDescription" : $("#ct_desc").val()
	});

	$.ajax({
		type : "POST",
		data : dt,
		url : "cinema-theatre",
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			console.log("Cinema/theatre created succesfully.");
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

function read_users(){
	$.get("user", function(data){
		for(i = 0; i < data.length; i++){
			var t = $("#user-table")[0];
			var row = t.insertRow(1);
			var cell1 = row.insertCell(0);
			var cell2 = row.insertCell(1);
			var cell3 = row.insertCell(2);
			
			cell1.innerHTML = data[i].userName;
			
			var btn1 = $("<button/>", {
				id: "user-" + data[i].userId
			});
			var btn2 = $("<button/>", {
				id: "user-" + data[i].userId
			});
			var btn3 = $("<button/>", {
				id: "user-" + data[i].userId
			});
			
			var div = $("<div/>", {
				id: "user-btns-" + data[i].userId
			});
			
			var div_type = $("<div/>", {
				id: "user-type-" + data[i].userId
			});
			
			if(data[i].userAdmin == 'N'){
				div_type.html("NORMAL");
				cell2.innerHTML = div_type[0].outerHTML;
				
				btn1[0].innerHTML = "FANZONE ADMIN";
				btn2[0].innerHTML = "CINEMA/THEATRE ADMIN";
				btn3[0].innerHTML = "SYSTEM ADMIN";
				
				btn1.attr("onclick", "setFZAdmin(this)");
				btn2.attr("onclick", "setCTAdmin(this)");
				btn3.attr("onclick", "setSysAdmin(this)");
				
				div[0].innerHTML = btn1[0].outerHTML + " " + btn2[0].outerHTML + " " + btn3[0].outerHTML;
				
				cell3.innerHTML = div[0].outerHTML;
			}else if(data[i].userAdmin == 'F'){
				div_type.html("FANZONE ADMIN");
				cell2.innerHTML = div_type[0].outerHTML;
				
				btn1[0].innerHTML = "NORMAL";
				btn2[0].innerHTML = "CINEMA/THEATRE ADMIN";
				btn3[0].innerHTML = "SYSTEM ADMIN";
				
				btn1.attr("onclick", "setNormalUser(this)");
				btn2.attr("onclick", "setCTAdmin(this)");
				btn3.attr("onclick", "setSysAdmin(this)");
				
				div[0].innerHTML = btn1[0].outerHTML + " " + btn2[0].outerHTML + " " + btn3[0].outerHTML;
				
				cell3.innerHTML = div[0].outerHTML;
			}else if(data[i].userAdmin == 'C'){
				div_type.html("CINEMA/THEATRE ADMIN");
				cell2.innerHTML = div_type[0].outerHTML;
				
				btn1[0].innerHTML = "NORMAL";
				btn2[0].innerHTML = "FANZONE ADMIN";
				btn3[0].innerHTML = "SYSTEM ADMIN";
				
				btn1.attr("onclick", "setNormalUser(this)");
				btn2.attr("onclick", "setFZAdmin(this)");
				btn3.attr("onclick", "setSysAdmin(this)");
				
				div[0].innerHTML = btn1[0].outerHTML + " " + btn2[0].outerHTML + " " + btn3[0].outerHTML;
				
				cell3.innerHTML = div[0].outerHTML;
			}else if(data[i].userAdmin == 'S'){
				div_type.html("SYSTEM ADMIN");
				cell2.innerHTML = div_type[0].outerHTML;
				
				btn1[0].innerHTML = "NORMAL";
				btn2[0].innerHTML = "FANZONE ADMIN";
				btn3[0].innerHTML = "CINEMA/THEATRE ADMIN";
				
				btn1.attr("onclick", "setNormalUser(this)");
				btn2.attr("onclick", "setFZAdmin(this)");
				btn3.attr("onclick", "setCTAdmin(this)");
				
				div[0].innerHTML = btn1[0].outerHTML + " " + btn2[0].outerHTML + " " + btn3[0].outerHTML;
				
				cell3.innerHTML = div[0].outerHTML;
			}else{
				cell2.innerHTML = "ERROR";
				cell3.innerHTML = "ERROR";
			}
			
			
		}
	});
}

function setNormalUser(e){
	var userID = e.id.split(/-(.+)/)[1];
	
	$.ajax({
		type : "PUT",
		url : "user/normal/" + userID,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			$("#user-type-" + userID).html("NORMAL");
			
			var d = $("#user-btns-" + userID)[0];
			d.innerHTML = "";
			
			var btn1 = $("<button/>", {
				id: "user-" + userID,
				html: "FANZONE ADMIN"
			});
			var btn2 = $("<button/>", {
				id: "user-" + userID,
				html: "CINEMA/THEATRE ADMIN"
			});
			var btn3 = $("<button/>", {
				id: "user-" + userID,
				html: "SYSTEM ADMIN"
			});
			
			btn1.attr("onclick", "setFZAdmin(this)");
			btn2.attr("onclick", "setCTAdmin(this)");
			btn3.attr("onclick", "setSysAdmin(this)");
			
			d.innerHTML = btn1[0].outerHTML + " " + btn2[0].outerHTML + " " + btn3[0].outerHTML;
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

function setFZAdmin(e){
	var userID = e.id.split(/-(.+)/)[1];
	
	$.ajax({
		type : "PUT",
		url : "user/fanzone/" + userID,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			$("#user-type-" + userID).html("FANZONE ADMIN");
			
			var d = $("#user-btns-" + userID)[0];
			d.innerHTML = "";
						
			var btn1 = $("<button/>", {
				id: "user-" + userID,
				html: "NORMAL"
			});
			var btn2 = $("<button/>", {
				id: "user-" + userID,
				html: "CINEMA/THEATRE ADMIN"
			});
			var btn3 = $("<button/>", {
				id: "user-" + userID,
				html: "SYSTEM ADMIN"
			});
			
			btn1.attr("onclick", "setNormalUser(this)");
			btn2.attr("onclick", "setCTAdmin(this)");
			btn3.attr("onclick", "setSysAdmin(this)");
			
			d.innerHTML = btn1[0].outerHTML + " " + btn2[0].outerHTML + " " + btn3[0].outerHTML;
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

function setCTAdmin(e){
var userID = parseInt(e.id.split(/-(.+)/)[1]);
	
	$.ajax({
		type : "PUT",
		url : "user/ct/" + userID,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			$("#user-type-" + userID).html("CINEMA/THEATRE ADMIN");
			
			var d = $("#user-btns-" + userID)[0];
			d.innerHTML = "";
						
			var btn1 = $("<button/>", {
				id: "user-" + userID,
				html: "NORMAL"
			});
			var btn2 = $("<button/>", {
				id: "user-" + userID,
				html: "FANZONE ADMIN"
			});
			var btn3 = $("<button/>", {
				id: "user-" + userID,
				html: "SYSTEM ADMIN"
			});
			
			btn1.attr("onclick", "setNormalUser(this)");
			btn2.attr("onclick", "setFZAdmin(this)");
			btn3.attr("onclick", "setSysAdmin(this)");
			
			d.innerHTML = btn1[0].outerHTML + " " + btn2[0].outerHTML + " " + btn3[0].outerHTML;
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

function setSysAdmin(e){
var userID = parseInt(e.id.split(/-(.+)/)[1]);
	
	$.ajax({
		type : "PUT",
		url : "user/system/" + userID,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			$("#user-type-" + userID).html("SYSTEM ADMIN");
			
			var d = $("#user-btns-" + userID)[0];
			d.innerHTML = "";
						
			var btn1 = $("<button/>", {
				id: "user-" + userID,
				html: "NORMAL"
			});
			var btn2 = $("<button/>", {
				id: "user-" + userID,
				html: "FANZONE ADMIN"
			});
			var btn3 = $("<button/>", {
				id: "user-" + userID,
				html: "CINEMA/THEATRE ADMIN"
			});
			
			btn1.attr("onclick", "setNormalUser(this)");
			btn2.attr("onclick", "setFZAdmin(this)");
			btn3.attr("onclick", "setCTAdmin(this)");
			
			d.innerHTML = btn1[0].outerHTML + " " + btn2[0].outerHTML + " " + btn3[0].outerHTML;
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