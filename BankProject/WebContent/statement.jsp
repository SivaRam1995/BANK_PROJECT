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
	margin-left: 80px;
	margin-top: 85px;
	margin-bottom: 200px;
	box-shadow: 20px 40px 80px rgba(0, 0, 0, 0.9);
	transition: 0.8s;
	width: 500px;
	height: 500px;
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
	height: 500px;
}

.left {
	margin-left: 100px;
	position: relative;
	display: inline-block;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.9);
	transition: 0.3s;
	height: 500px;
}

.btn-primary {
	width: 100%;
}

iframe {
	height: 490px;
	width: 100%;
	border: 2px solid #fff;
	background-image: url("images/check.jpg");
	box-shadow: 20px 40px 80px rgba(0, 0, 0, 0.9);
}

table {
	background: rgba(0, 0, 0, 0.1);
}

.table {
	width: 80%;
	margin-left: 125px;
	margin-top: 30px;
	margin-bottom: 150px;
	font-weight: 900;
	background-color: white;
	box-shadow: 45px 85px 120px #000;
}

h1 {
	text-shadow: 12px 15px 25px #000;
	color: #fff;
	text-align: center;
}

th {
	font-size: 22px;
}

tr:hover {
	background: rgba(0, 0, 0, 0.8);
}

#right {
	float: left;
	display: inline;
}

.search {
	display: inline;
	width: 300px;
	margin-left: 5px;
}

.btn-success {
	width: 250px;
	font-weight: 900;
	margin: 10px 25px;
}

.btn {
	text-shadow: 5px 8px 18px #000000;
	font-weight: 900;
	box-shadow: 2px 5px 12px #000;
}

@media screen and (max-width: 1366px) and (max-height: 768px) {
	.card {
		margin-left: 125px;
		margin-top: 15px;
		margin-bottom: 100px;
		box-shadow: 20px 40px 80px rgba(0, 0, 0, 0.9);
		transition: 0.8s;
		width: 400px;
		height: 450px;
		border: 3px solid teal;
		float: left;
		background-color: #EEEEEE;
		opacity: 0.9;
	}
	.re{
	margin-left:50px;}
	.t
	{
	width:640px;}
	
}
</style>
</head>
<body>
<form action="SearchStatement">

		<div id="right" class="re">
			<button type="submit" class="btn btn-primary se">GetStatement</button>
		</div>
		<div id="right">
			<input type="text" placeholder="Only Enter Any Acc Number To Search Statement / select date / Enter <- both ->"
				class="form-control search t" name="accnum"><input
				type="date" class="form-control search" name="searchdate">
		</div>


	</form>

	<form action="GetStatement" method="post">

		<div class="card" style="width: 25rem;">
			<img src="images/money.gif" class="card-img-top">

			<div class="card-body">
				<input type="submit" class="btn btn-primary"
					value="GET ALL TRANSACTION DETAILS">
			</div>
		</div>

	</form>
	
	<p class="end"></p>

</body>
</html>