var services = {
	
	call : function(obj, callback, parent){
		$.ajax({
		    type: 'post', 
		    url: parent ? '../coresvc' : './coresvc',
		    dataType: 'json',
		    data: JSON.stringify(obj),
		    complete: callback
		});
	},
	
		
	showErrorAlert : function(id, msg, type){
		var type = type || "danger";
		$("#"+id)
			.html('<div class="alert alert-' +type+ '"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +msg+ '</div>')
			.hide()
			.fadeIn();
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
};