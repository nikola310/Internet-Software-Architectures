$( document ).ready(function() {
	loadTheatres();
	loadCinemas();
});


function loadTheatres() {
	$.ajax({
		url : 'cinema-theatre/theatres',
		type : 'GET',
		dataType : 'json',
		error : function(data) {
		},
		success : function(data) {
			$("#tList").empty(); // cistimo, ako je bilo vec neceg
			$.each(data, function(index, element) {
						var newHTML = [];
						newHTML.push("<button type=\"button\" class=\"ui-btn ui-shadow ui-corner-all\" id=\"theatre-"+element.ctId+"\">"+element.ctName+"</button>");
						$("#tList").append(newHTML.join(""));
						$("#theatre-"+element.ctId).click(function(){
							$.ajax({
						  		type: "POST",
						  		url: "cinema-theatre/"+element.ctId+"/select",
						  		success: function() {
						  			window.location.replace("cinemaTheatre.html");
						  		},
						  		error: function() {
						  		}
						  	});
						});
			});
		}
	});
}
function loadCinemas() {
	$.ajax({
		url : 'cinema-theatre/cinemas',
		type : 'GET',
		dataType : 'json',
		error : function(data) {
		},
		success : function(data) {
			$("#cList").empty(); // cistimo, ako je bilo vec neceg
			$.each(data, function(index, element) {
						var newHTML = [];
						newHTML.push("<button type=\"button\" class=\"ui-btn ui-shadow ui-corner-all\" id=\"cinema-"+element.ctId+"\">"+element.ctName+"</button>");
						$("#cList").append(newHTML.join(""));
						$("#cinema-"+element.ctId).click(function(){
							$.ajax({
						  		type: "POST",
						  		url: "cinema-theatre/"+element.ctId+"/select",
						  		success: function() {
						  			window.location.replace("cinemaTheatre.html");
						  		},
						  		error: function() {
						  		}
						  	});
						});
			});
		}
	});
}	
	
	













	