/**
 * @author Zivko Stanisic
 */


function login() {
	var regData = JSON.stringify({        
	        "email": $("#mail").val(),
	        "password": $("#password").val()        
	    });

	
	$.ajax({
        type: 'post',
        url: 'user/login',
        data: regData,
        contentType: "application/json; charset=utf-8",
        dataType : "json",
        success: function (data) {
        	window.location.replace("index.html");
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