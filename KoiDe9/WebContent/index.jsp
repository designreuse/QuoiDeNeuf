<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	hello
	
	<div id="rep"></div>

<script src="./js/jq.js"></script>
<script>
	var obj = { 
			nomService: "LireUtilisateur", 
			data: {
				nom: "chat",
				login: "kaiji3", 
				mdp: "aaa",
				photo: "moche",
				description : "alors"
			}
	}; 

$.ajax({
    type: 'post', // it's easier to read GET request parameters
    url: './coresvc',
    dataType: 'json',
    data: JSON.stringify(obj),
    complete: function(data) {
    	
        $("#rep").html(data.responseText);
        console.log(data.responseText);
        
    }
});

 







</script>




</body>
</html>