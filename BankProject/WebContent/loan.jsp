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
	background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.5)),
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
	margin-left: 175px;
	margin-top: 5px;
	margin-bottom: 100px;
	box-shadow: 20px 40px 80px rgba(0, 0, 0, 0.9);
	transition: 0.8s;
	width: 500px;
	border : 3px solid teal;
	float: left;
	background-color: #EEEEEE;
	opacity: 0.9;
	border: 3px solid teal;
}

.card:hover {
	box-shadow: 0 8px 16px 0 rgba(0, 0, 0, 0.2);
}

img {
	width: 100%;
	height: 500px;
	box-shadow: 12px 15px 35px #000;
}

.left {
	margin-left: 100px;
	position: relative;
	display: inline-block;
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.9);
	transition: 0.4s;
	height: 500px;
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

#end {
	margin-bottom: 200px;
}

.btn {
	box-shadow: 12px 15px 25px #000;
	padding: 20px;
	font-weight: 900;
	text-shadow: 2px 8px 12px #000;
	width: 100%;
	font-size: 22px;
}

.btn:hover {
	background-color: #212529;
}

.card-body {
	positin: relative;
	padding: 0px;
	margin: 0px;
}

@media screen and (width: 1366px) and (max-height: 768px) {
	.card {
		margin-left: 125px;
		margin-top: 2px;
		margin-bottom: 100px;
		box-shadow: 20px 40px 80px rgba(0, 0, 0, 0.9);
		transition: 0.8s;
		width: 500px;
		height:470px;
		border: 3px solid teal;
		float: left;
		background-color: #EEEEEE;
		opacity: 0.9;
	}
	.btn {
		font-size: 18px;
		padding: 15px;
	}
}
</style>
</head>
<body>

	<div class="card" style="width: 25rem;">
		<img src="images/veichel.gif" class="card-img-top" alt="check Balance"
			title="">

		<div class="card-body">
			<a href="veichelloan.jsp" class="btn btn-primary">APPLY VEICHEL
				LOAN</a>
		</div>
	</div>
	<div class="card" style="width: 25rem;">
		<img src="images/homeloan.gif.crdownload" class="card-img-top"
			alt="check Balance" title="">

		<div class="card-body">
			<a href="homeloan.jsp" class="btn btn-primary">APPLY HOME LOAN</a>

		</div>
	</div>
	<div class="card" style="width: 25rem;">
		<img src="images/educate.png" class="card-img-top" alt="check Balance"
			title="">

		<div class="card-body">
			<a href="educationloan.jsp" class="btn btn-primary">APPLY
				EDUCATION LOAN</a>
		</div>
	</div>
	<div class="card" style="width: 25rem;">
		<img src="images/personalloan.gif" class="card-img-top"
			alt="check Balance" title="">

		<div class="card-body">
			<a href="marriageloan.jsp" class="btn btn-primary">APPLY
				PERSONAL LOAN</a>
		</div>
	</div>


	<p class="end"></p>

</body>
</html>