/**
 * @author Zivko Stanisic
 */


function registrate() {
	var regData = JSON.stringify({        
	        "userEmail": $("#userEmail").val(),
	        "userName": $("#userName").val(),
	        "userSurname": $("#userSurname").val(),
	        "userPassword": $("#userPassword").val(),
	        "userCity": $("#userCity").val(),
	        "userStateid": $("#userStateid").val(),
	        "userPhone": $("#userPhone").val()
	        
	    });

	
	$.ajax({
        type: 'post',
        url: 'user',
        data: regData,
        contentType: "application/json; charset=utf-8",
        dataType : "json",
        success: function (data) {
        	alert("User succesfully registered");
        	window.location.replace("fanzone.html");
        },
        fail : function(data) {
        	console.log(regData);
			console.log(data);
		},
		error: function(data){
			console.log(regData);
			console.log(data);
		}
    });	
	return false;
}
