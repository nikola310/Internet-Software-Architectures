/**
 * @author Zivko Stanisic
 */
var email;
$(document).ready(function() {
	$.get("user/profile", function(data) {
		email = data.userEmail;
		$("#userName").val(data.userName);
		$("#userSurname").val(data.userSurname);
		$("#userPassword").val(data.userPassword);
		$("#userCity").val(data.userCity);
		$("#userStateid").val(data.userStateid);
		$("#userPhone").val(data.userPhone);

	});
});

function edit() {
	var regData = JSON.stringify({
		"userEmail" : email,
		"userName" : $("#userName").val(),
		"userSurname" : $("#userSurname").val(),
		"userPassword" : $("#userPassword").val(),
		"userCity" : $("#userCity").val(),
		"userStateid" : $("#userStateid").val(),
		"userPhone" : $("#userPhone").val()

	});

	$.ajax({
		type : 'post',
		url : 'user/edit',
		data : regData,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		success : function(data) {
			window.location.replace("userProfile.html");
		},
		fail : function(data) {
			console.log(regData);
			console.log(data);
		},
		error : function(data) {
			console.log(regData);
			console.log(data);
		}
	});
	return false;
}
