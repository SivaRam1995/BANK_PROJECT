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
<title>My Profile</title>
<link rel="stylesheet" href="style/bootstrap.css">
<style type="text/css">
body {
	width: 85%;
	margin-left: 100px;
	margin-top: 5px;
	height: 100%;
	background-image: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.5)),
		url("images/baground.jpg");
	background-size: cover;
	background-attachment: fixed;
}

::-webkit-scrollbar {
	width: 0;
}

table {
	margin-top: 10px;
	opacity: 0.9;
	box-shadow: 12px 15px 45px #000;
}

#end {
	margin-bottom: 100px;
}

img {
	margin-top: 50px;
	margin-left: 330px;
	border-radius: 10px;
	border: 5px solid #fff;
	box-shadow: 12px 25px 85px #000;
	margin-bottom: 60px;
	opacity: 1;
}

img:hover {
	
}

b {
	margin-left: 200px;
	font-size: 18px;
	text-shadow: 12px 15px 20px #000;
}

#or {
	color: orange;
}

.btn {
	width: 100%;
}

@media screen and (max-width: 1366px) and (max-height: 768px) {
	img {
		margin-left: 320px;
	}
}
</style>
</head>
<body>

	<%
	String name = (String) session.getAttribute("name");
		String logid = (String) session.getAttribute("loginid");
		String pass = (String) session.getAttribute("pass");
		String photo = (String) session.getAttribute("photo");
		String dob = (String) session.getAttribute("dob");
		String email = (String) session.getAttribute("email");
		String mobile = (String) session.getAttribute("mobile");
		String aadhar = (String) session.getAttribute("aadhar");
		String gender = (String) session.getAttribute("gender");
		String nationality = (String) session.getAttribute("nationality");
		String designation = (String) session.getAttribute("designation");
	%>
	<form action="SaveEmp">
		<table class="table table-bordered table-dark table-striped">
			<tr>
				<td colspan="2"><img src="<%=photo%>"
					style="width: 400px; height: 400px;"></td>
			</tr>
			<tr>
				<td><b>Bank Name</b></td>
				<td><b id="or">ABC BANK</b></td>
			</tr>
			<tr>
				<td><b>Designation</b></td>
				<td><b id="or"><%=designation%></b></td>
			</tr>

			<tr>
				<td><b>My Name</b></td>
				<td><b id="or"><%=name%></b></td>
			</tr>
			<tr>
				<td><b>LogIn Id</b></td>
				<td><input type="text" class="form form-control"
					value="<%=logid%>" name="log" required="required"></td>
			</tr>
			<tr>
				<td><b>password</b></td>
				<td><input type="text" class="form form-control"
					value="<%=pass%>" name="password" required="required"></td>
			</tr>
			<tr>
				<td><b>Date Of Birth</b></td>
				<td><b id="or"><%=dob%></b></td>
			</tr>

			<tr>
				<td><b>Email</b></td>
				<td><b id="or"><%=email%></b></td>
			</tr>
			<tr>
				<td><b>Mobile Number</b></td>
				<td><b id="or"><%=mobile%></b></td>
			</tr>
			<tr>
				<td><b>Aadhar No.</b></td>
				<td><b id="or"><%=aadhar%></b></td>
			</tr>
			<tr>
				<td><b>Gender</b></td>
				<td><b id="or"><%=gender%></b></td>
			</tr>
			<tr>
				<td><b>Nationality</b></td>
				<td><b id="or"><%=nationality%></b></td>
			</tr>
			<tr>
				<td><a href="empprofile.jsp" class="btn btn-primary">CANCEL</a></td>
				<td><button type="submit" class="btn btn-primary">SAVE</button></td>
			</tr>

		</table>
	</form>
	<p id="end"></p>


</body>
</html>