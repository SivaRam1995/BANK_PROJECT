<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>checkBalance</title>
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
	margin-bottom: 100px;
}

.card {
	margin-left: 200px;
	margin-top: 6px;
	box-shadow: 20px 40px 80px rgba(0, 0, 0, 0.9);
	transition: 0.8s;
	width: 500px;
	height: 550px;
	border: 2px solid teal;
	float: left;
	background-color: #EEEEEE;
	opacity: 0.7;
}

.card:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

img {
	width: 100%;
	height: 550px;
}

.left {
	margin-left: 100px;
	position: relative;
	display: inline-block;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.9);
	transition: 0.3s;
	height: 550px;
}

.btn-primary {
	width: 100%;
}

iframe {
	height: 550px;
	width: 100%;
	border: 2px solid #fff;
	background-image: url("images/check.jpg");
	box-shadow: 20px 40px 80px rgba(0, 0, 0, 0.9);
}

@media screen and (max-width: 1366px) and (max-height: 768px) {
	.card {
	height:450px;
	}
	left{
	height:450px;
	}
	iframe{
	height:450px;}
}
</style>
</head>
<body>
	<form action="viewBalance">
		<div class="card" style="width: 25rem;">
			<img src="images/money.gif" class="card-img-top">

		</div>
	</form>
	<div class="card left" style="width: 25rem;" id="show">
		<iframe src="balance.jsp"></iframe>
	</div>


	<p class="end"></p>
	
</body>
</html>