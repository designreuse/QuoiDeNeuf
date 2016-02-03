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
	
		
	showErrorAlert : function(id, msg){
		$("#"+id).html('<div class="alert alert-danger"><a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>' +msg+ '</div>');
	}
	
	
	

	
	
	
	
	
	
	
	
	
	
	
};