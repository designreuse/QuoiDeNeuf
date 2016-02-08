var services = {
	
	call : function(obj, parent){
		return $.ajax({
		    type: 'post', 
		    url: parent ? '../coresvc' : './coresvc',
		    dataType: 'json',
		    data: JSON.stringify(obj)
		}).promise();
	},
	
		
	showErrorAlert : function(id, msg, type){
		var type = type || "danger";
		$("#"+id)
			.html('<div class="alert alert-' +type+ ' palert"><span class="glyphicon glyphicon-flag"></span> <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a> ' +msg+ '</div>')
			.hide()
			.slideDown(200)
			.delay(5000)
			.slideUp(200);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
};