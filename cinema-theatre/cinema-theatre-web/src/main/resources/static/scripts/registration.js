/**
 * @author Zivko Stanisic
 */


$( "#confrim" ).click(function() {
	var domain = {
	        userName: $("#useName").val(),
	        userSurname: $("#useSurname").val(),
	        userEmail: $("#userEmail").val(),
	        userCity: $("#userCity").val(),
	        userPhone: $("#userPhone").val(),
	        userStateid: $("#userStateid").val(),
	        userPassword: $("#userPassword").val()
	    }
	
	$.ajax({
        type: 'post',
        url: 'http://localhost:8080/cinema-theatre/user',
        data: $.toJSON(domain),
        contentType: "application/json; charset=utf-8",
        traditional: true,
        success: function (data) {
            
        }
    });	
});