<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.bank.resource.Template"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.util.Base64"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>HOME</title>
<link rel="stylesheet" href="style/bootstrap.css">
<style>
body {
	margin-top: 8%;
	background-image: linear-gradient(rgba(0, 0, 0, 0.6), rgba(0, 0, 0, 0.5)),
		url("images/baground.jpg");
	background-size: cover;
	background-attachment: fixed;
	opacity: 0.9;
}

h1 {
	margin-top: 5%;
	color: orange;
	font-size: 60px;
	text-align: center;
	text-shadow: 5px 7px 10px #000;
	font-family: cursive;
}

#date {
	color: orange;
	margin-left: 950px;
	font-weight: 900;
	font-family: cursive;
	text-shadow: 2px 5px 8px #000;
	font-size: 30px;
}

img {
	width: 300px;
	height: 300px;
	border-radius: 50%;
	border: 4px solid orange;
	margin-left: 500px;
	box-shadow: 18px 20px 145px rgba(0, 0, 0, 0.9);
	opacity: 1;
}

img:hover {
	border: 6px solid orange;
	transition: 0.2s;
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
	img {
		margin-left: 430px;
		height:320px;width:320px;
	}
}

</style>
</head>
<body>
	<%
	Connection conn = Template.getMySqlConnection();
		String output = null;
		String photo = (String) session.getAttribute("photo");
	%>

	<p id="date">
		Date:
		<script>
			document.write(new Date().toLocaleDateString());
		</script>
	</p>
	<table>
		<tr>
			<td><a href="empprofile.jsp" title="click To See My Profile"><img
					src="<%=photo%>" id="img"></a></td>
		</tr>
	</table>
	<h1>WELCOME TO ABC BANK</h1>


</body>
</html>