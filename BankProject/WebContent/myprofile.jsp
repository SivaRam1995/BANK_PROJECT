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
	box-shadow: 10px 15px 65px #000;
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
border-color:orange;
	
}

b {
	margin-left: 200px;
	font-size: 18px;
	text-shadow: 12px 15px 20px #000;
}

#or {
	color: orange;
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
	Connection conn = Template.getMySqlConnection();
		String output = null;
		String name = (String) session.getAttribute("logid");
		String pass = (String) session.getAttribute("pass");
		String sql = "select * from banklog where loginid=? and password=?";
		PreparedStatement pstmnt = conn.prepareStatement(sql);
		pstmnt.setString(1, name);
		pstmnt.setString(2, pass);
		ResultSet res = pstmnt.executeQuery();

		String bankname = null, acctype = null, accnum = null, profile = null, username = null, login = null,
				password = null, email = null, dob = null, mobile = null, gen = null, aadhar = null,
				nationality = null;
		int pin = 0;

		while (res.next()) {
			bankname = res.getString(2);
			acctype = res.getString(3);
			accnum = res.getString(4);
			pin = res.getInt(5);
			Blob blob = res.getBlob(6);
			if (blob != null) {

				int len = (int) blob.length();
				byte[] imgBytes = blob.getBytes(1L, len);
				String encodedImg = Base64.getEncoder().encodeToString(imgBytes);
				profile = "data:image/png;base64," + encodedImg;
			}
			username = res.getString(7);
			login = res.getString(8);
			password = res.getString(9);
			email = res.getString(10);
			dob = res.getString(11);
			mobile = res.getString(12);
			gen = res.getString(13);
			aadhar = res.getString(14);
			nationality = res.getString(15);
			session.setAttribute("pin", pin);
			session.setAttribute("pass", password);
			session.setAttribute("log", login);
	
		}
	%>

	<table class="table table-bordered table-dark table-striped">
		<tr>
			<td colspan="2"><a href="myprofileedit.jsp" id="profile"
				title="click to edit my profile"><img src="<%=profile%>"
					style="width: 400px; height: 400px;"></a></td>
		</tr>
		<tr>
			<td><b>Bank Name</b></td>
			<td><b id="or"><%=bankname%></b></td>
		</tr>
		<tr>
			<td><b>Account Type</b></td>
			<td><b id="or"><%=acctype%></b></td>
		</tr>
		<tr>
			<td><b>Account Number</b></td>
			<td><b id="or"><%=accnum%></b></td>
		</tr>
		<tr>
			<td><b>Pin Number</b></td>
			<td><b id="or"><%=pin%></b></td>
		</tr>
		<tr>
			<td><b>My Name</b></td>
			<td><b id="or"><%=username%></b></td>
		</tr>

		<tr>
			<td><b>Login Id</b></td>
			<td><b id="or"><%=login%></b></td>
		</tr>
		<tr>
			<td><b>Password</b></td>
			<td><b id="or"><%=password%></b></td>
		</tr>
		<tr>
			<td><b>Email Id</b></td>
			<td><b id="or"><%=email%></b></td>
		</tr>
		<tr>
			<td><b>Date Of Birth</b></td>
			<td><b id="or"><%=dob%></b></td>
		</tr>
		<tr>
			<td><b>Mobile Number</b></td>
			<td><b id="or"><%=mobile%></b></td>
		</tr>
		<tr>
			<td><b>Gender</b></td>
			<td><b id="or"><%=gen%></b></td>
		</tr>
		<tr>
			<td><b>Aadhar No.</b></td>
			<td><b id="or"><%=aadhar%></b></td>
		</tr>
		<tr>
			<td><b>Nationality</b></td>
			<td><b id="or"><%=nationality%></b></td>
		</tr>

	</table>
	<p id="end"></p>


</body>
</html>