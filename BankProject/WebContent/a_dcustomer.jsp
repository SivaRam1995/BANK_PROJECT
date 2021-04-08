<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page import="com.bank.dto.SearchAcNumbers" %>
	<%@ page import="java.util.List" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>status</title>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">  
  <script src="https://code.jquery.com/jquery-1.10.2.js"></script>  
  <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script> 
    <link rel="stylesheet" href="style/bootstrap.css">
 
<link rel="stylesheet" href="style/bootstrap.css">
<style>
body {
	margin: 0;
	padding: 0;
	overflow: hidden;
	background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.6)),
		url("images/baground.jpg");
	background-attachment: fixed;
	background-size: cover;
	overflow-y: scroll;
}

::-webkit-scrollbar {
	width: 0px;
}

.end {
	margin-bottom: 40px;
}

.btn-primary {
	width: 20%;
}

.search {
	display: inline;
}

.btn {
	text-shadow: 5px 8px 18px #000000;
	font-weight: 900;
	box-shadow: 12px 15px 25px #000;
	padding: 10px;
	margin-top: -5px;
}

.form-control {
	box-shadow: 12px 15px 25px #000;
	width: 50%;
	padding: 10px;
}

#right {
	margin-left: 300px;
	margin-top: 50px;
}

#date {
	color: #fff;
	margin-left: 950px;
	font-weight: 900;
	font-family: cursive;
	text-shadow: 2px 5px 8px #000;
	font-size: 30px;
	margin-top: 15px;
}

iframe {
	width: 100%;
	height: 500px;
	position: fixed;
	background: none;
}

table {
	opacity: 0.9;
	margin-bottom:0px;
}

img {
	box-shadow: 12px 15px 25px rgba(0, 0, 0, 0.9);
	border: 2px solid orange;
	width:400px;
	height:400px;
}

b {
	font-weight: 900;
	font-size: 16px;
	text-shadow: 12px 15px 12px #000;
}

span {
	font-weight: 900;
	font-size: 16px;
	color: orange;
	margin-left: 5px;
	text-shadow: 12px 15px 12px #000;
}

.se {
	margin-left: 650px;
}

caption {
	color: orange;
}
@media screen and (max-width: 1366px) and (max-height: 768px)
{
#date{
color:orange;
margin-left:900px;
font-weight: 900;
font-family:cursive;
text-shadow:2px 5px 8px #000;
font-size:30px;
margin-top:60px;
}
#right{
        margin-left:150px;
        margin-top:30px;}
        .search{
        width:60%;}

      
}

</style>
</head>
<body>
	<p id="date">
		Date:
		<script>
			document.write(new Date().toLocaleDateString());
		</script>
	</p>
	<div id="right">

		<form action="ChangeStatus" method="post">



			<button type="submit" class="btn btn-primary ">CHECK STATUS</button>
			<input type="number" placeholder="Enter Account Number " id="tags"
				class="form-control search" name="accnum" required="required">


		</form>
	</div>
	<p class="end"></p>
	
 <%SearchAcNumbers get=new SearchAcNumbers();
	List<String> allAcNumbers=get.allAcNumbers();%>
<script>  
  $(function() {  
	  var availableTags =<%=allAcNumbers%>;
    $( "#tags" ).autocomplete({  
      source: availableTags  
    });  
  });  
  </script>
       
	<iframe name="activityframe"></iframe>
</body>
</html>