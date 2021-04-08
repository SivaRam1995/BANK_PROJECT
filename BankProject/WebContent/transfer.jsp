<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style/bootstrap.css">
<style>
body {
	margin: 0;
	padding: 0;
	background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.7)),
		url("images/baground.jpg");
	background-attachment: fixed;
	background-size: cover;
}

::-webkit-scrollbar {
	width: 0px;
}

form {
	background-color: #EEEEEE;
	width: 60%;
	margin-left:250px;
	padding: 40px;
	margin-top: 40px;
	margin-bottom: 150px;
	border: 1px solid teal;
	box-shadow: 50px 29px 105px rgba(0, 0, 0, 1);
	border-radius: 10px;
	opacity: 0.8;
	color: #000000;
}

form:hover {
	box-shadow: 80px 180px 150px rgba(0, 0, 0, 0.9);
}

.btn {
	width: 100%;
	font-weight: 900;
	margin-top: 40px;
}

h1 {
	margin-left: 100px;
	margin-bottom: 25px;
	font-weight: 900;
}

.form-control {
	margin-top: 10px;
	padding: 10px;
}

.form-label {
	margin-top: 30px;
}

@media screen and (width: 1366px) and (max-height: 768px) {
	form {
		margin-top: 15px;
		padding: 30px;
		width: 60%;
		margin-left: 230px;
	}
	.form-control {
		margin-top: 5px;
		padding: 5px;
	}
}
</style>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-strore, must-revalidate");
	%>

	<form action="Transfer">
		<h1>MONEY TRANSFER</h1>
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">ACCOUNT
				NUMBER</label> <input type="number" class="form-control" name="acc"
				aria-describedby="emailHelp"
				placeholder="Enter Receiver Account Number" required="required">
		</div>
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">AMOUNT</label> <input
				type="number" class="form-control" name="amount"
				aria-describedby="emailHelp" placeholder="Enter Amount "
				required="required">
		</div>
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">PIN</label> <input
				type="number" class="form-control" name="pin"
				aria-describedby="emailHelp" placeholder="Enter Your Pin Number"
				required="required">
			<div id="emailHelp" class="form-text">We'll never share your
				details with anyone else.</div>
		</div>

		<button type="submit" class="btn btn-primary">Transfer</button>

	</form>

</body>
</html>