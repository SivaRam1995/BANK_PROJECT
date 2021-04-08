<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.bank.dto.SearchAcNumbers"%>
<%@ page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>checkBalance</title>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
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
	margin-bottom: 100px;
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
	margin-top: 100px;
	display: inline;
	box-shadow: 12px 15px 25px #000;
	width: 40%;
	padding: 10px;
}

.form-select {
	box-shadow: 12px 15px 25px #000;
	width: 25%;
	padding: 10px;
	display: inline;
}

#right {
	display: inline;
	margin-left: 50px;
}

#date {
	color: #fff;
	margin-left: 950px;
	font-weight: 900;
	font-family: cursive;
	text-shadow: 2px 5px 8px #000;
	font-size: 30px;
	margin-top: 30px;
}

table {
	width: 98%;
	text-shadow: 12px 15px 25px #000;
}

h1 {
	text-align: center;
	color: #fff
}

th {
	color: orange;
}

caption {
	color: orange;
}

#btnExport {
	width: 250px;
	margin-top: 10px;
	margin-left: 40px;
}

@media screen and (max-width: 1366px) and (max-height: 768px) {
	#date {
		color: orange;
		margin-left: 900px;
		font-weight: 900;
		font-family: cursive;
		text-shadow: 2px 5px 8px #000;
		font-size: 30px;
	}
	#right {
		margin-left: 80px;
	}
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


	<form action="MiniStatement">


		<div id="right">
			<button type="submit" class="btn btn-primary se" id="searching">MINI
				STATEMENT</button>
			<input type="number" placeholder="Enter Account Number "
				class="form-control search" id="tags" name="accnum"
				required="required">
		</div>
		<select class="form-select" required="required" name="stmnt">
			<option value="5" selected>SELECT (By Default 5 Selected)</option>
			<option value="10">10</option>
			<option value="15">15</option>
			<option value="20">20</option>
		</select>

	</form>

	<p class="end"></p>
	<%
	SearchAcNumbers get = new SearchAcNumbers();
		List<String> allAcNumbers = get.allAcNumbers();
	%>
	<script>
		$(function() {
			var availableTags =
	<%=allAcNumbers%>
		;
			$("#tags").autocomplete({
				source : availableTags
			});
		});
	</script>

</body>
</html>