$( document ).ready(function() {
	loadData();
});

function loadData() {
	$.ajax({
		url : 'cinema-theatre/selected',
		type : 'GET',
		dataType : 'json',
		error : function(data) {
		},
		success : function(data) {
			
		}
	});
}