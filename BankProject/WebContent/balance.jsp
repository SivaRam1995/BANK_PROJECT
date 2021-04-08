<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet" href="style/bootstrap.css">
<style>
body {
	margin: 0px;
	padding: 0px;
	background-image: url("images/check.jpg");
	background: rgba(0, 0, 0, 0.2);
}

::-webkit-scrollbar {
	width: 0px;
}

h1 {
	font-style: italic;
	font-family: cursive;
	font-size: 40px;
	line-height: 90px;
	text-align: center;
	text-shadow: 5px 8px 18px #000000;
	font-weight: 900;
}

hr {
	width: 70%;
}

.btn {
	width: 94%;
	margin-left: 10px;
	margin-top: 475px;
	text-shadow: 5px 8px 18px #000000;
	font-weight: 900;
	font-size: 22px;
	box-shadow: 12px 15px 25px #000;
	padding: 12px;
}

.card-body {
	padding: 0px;
	margin: 0px;
}

#show {
	background-color: #212529;
	color: #fff;
}

#rupee {
	color: orange;
}

@media screen and (max-width: 1366px) and (max-height: 768px) {
	.btn {
		margin-top: 370px;
	}
	h1{
	font-size:35px;
	line-height:80px;}
}
</style>
</head>
<body>
	<form action="viewBalance">
		<div class="card-body">
			<input type="submit" class="btn btn-primary" value="View Balance">
		</div>
	</form>


</body>
</html>