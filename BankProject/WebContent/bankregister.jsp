<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.bank.resource.Template"%>

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
	overflow-x: hidden;
}

::-webkit-scrollbar {
	width: 0px;
}

.right {
	float: left;
	width: 700px;
	height: 500px;
	background-color: white;
}

form {
	width: 700px;
	padding: 20px;
}

table, tr, td {
	width: 600px;
	height: 100%;
}

input[type="text"], input[type="password"], input[type="email"], input[type="number"],
	input[type="date"], select {
	padding: 15px;
	width: 100%;
	margin-top: 10px;
	border: none;
	border-bottom: 1px solid #000;
	outline: none;
}

input[type="password"]:focus {
	border-bottom: 2px solid green;
}

input[type="email"]:focus, [type="text"]:focus, input[type="date"]:focus
	{
	border-bottom: 2px solid green;
}

input[type="submit"] {
	width: 150px;
	padding: 10px;
	margin-top: 50px;
	margin-left: 220px;
	color: #f6f6f6;
	font-size: 14px;
	font-weight: 900;
	background-color: green;
	border: 1px solid green;
	box-shadow: 2px 3px 8px #000;
	cursor: pointer;
	margin-bottom: 100px;
}

input[type="submit"]:hover {
	background: #004d00;
}

h1 {
	margin-top: 50px;
	font-size: 35px;
	font-weight: 900;
	margin-left: 30px;
}

#image {
	display: block;
	padding: 8px;
	width: 250px;
	margin-left: 50px;
}

select {
	width: 100%;
	padding: 10px;
	width: 250px;
	margin-left: -0px;
}

#a {
	margin-top: 100px;
}

sup {
	color: red;
}
</style>

</head>
<body>


	<%
	Connection conn = Template.getMySqlConnection();
		String acc = (String) session.getAttribute("acc");
		String sql = "select * from banklog where accnum=?";
		PreparedStatement pstmnt = conn.prepareStatement(sql);
		pstmnt.setString(1, acc);
		ResultSet res = pstmnt.executeQuery();

		String bankname = null, acctype = null, accnum = null, username = null, login = null, password = null,
				email = null, dob = null, mobile = null, gen = null, aadhar = null, nationality = null;
		int pin = 0;

		while (res.next()) {
			bankname = res.getString(2);
			acctype = res.getString(3);
			accnum = res.getString(4);
			pin = res.getInt(5);
			username = res.getString(7);
			login = res.getString(8);
			password = res.getString(9);
			email = res.getString(10);
			dob = res.getString(11);
			mobile = res.getString(12);
			gen = res.getString(13);
			aadhar = res.getString(14);
			nationality = res.getString(15);
		}
	%>

	<h1>CREATE ABC ONLINE BANKING A/C</h1>
	<div class="right">
		<form action="Register" method="post">
			<table>

				<tr>
					<td><hr style="width: 100%; margin-left: 125px;"> <br>
						<!-- <b style="font-size:26px;">Add User</b> --> <br> <br>
						<b>Bank Details</b>
						<hr></td>
				</tr>

				<tr>
					<td>Bank Name<sup>*</sup></td>
					<td><select name="bankname" id="" required="required">
							<option value="ABC BANK" selected>ABC BANK</option>
					</select></td>
				</tr>
				<tr>
					<td>Type Of Account<sup>*</sup></td>
					<td><select name="acctype" id="" required="required">
							<option value="<%=acctype%>" selected><%=acctype%></option>
					</select></td>
				</tr>

				<tr>
					<td>Account Number<sup>*</sup></td>
					<td><select name="accnum" id="" required="required">
							<option value="<%=accnum%>" selected><%=accnum%></option>
					</select></td>
				</tr>
				<tr>
					<td>Pin Number<sup>*</sup></td>
					<td><input type="number" placeholder="Create pin Number"
						name="pin" pattern="/^-?\d+\.?\d*$/"
						onKeyPress="if(this.value.length==4) return false;"
						required="required"></td>
				</tr>


				<tr>
					<td id="a"><br> <b>User Details</b>
						<hr></td>
				</tr>

				<tr>
					<td>Enter Name<sup>*</sup></td>
					<td><select name="name" id="" required="required">
							<option value="<%=username%>" selected><%=username%></option>
					</select></td>
				</tr>
				<tr>
					<td>LogIn Id<sup>*</sup></td>
					<td><input type="text"
						placeholder="Create LoginID for future login" name="logid"
						required="required"></td>
				</tr>
				<tr>
					<td>Password<sup>*</sup></td>
					<td><input type="password" id="pass"
						placeholder="Enter password"
						pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
						onKeyPress="if(this.value.length==10) return false;"
						onchange="check_pass();" required="required"
						title="Must contain at least one number and one uppercase and lowercase letter, and at least 10 characters"
						name="pwd" required="required"></td>
				</tr>
				<tr>
					<td>Confirm password<sup>*</sup></td>
					<td><input type="password" id="cpass"
						placeholder="Re-Enter Password"
						pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
						onKeyPress="if(this.value.length==10) return false;"
						onchange="check_pass();" required="required"
						title="Must contain at least one number and one uppercase and lowercase letter, and at least 10 characters"
						required="required"></td>
				</tr>
				<tr>
					<td>Email Id<sup>*</sup></td>
					<td><select name="email" id="" required="required">
							<option value="<%=email%>" selected><%=email%></option>
					</select></td>
				</tr>
				<tr>
					<td id="a"><br> <br> <b>Personal Details</b>
						<hr></td>
				</tr>
				<tr>
					<td>Date Of Birth<sup>*</sup></td>
					<td><select name="dob" id="" required="required">
							<option value="<%=dob%>" selected><%=dob%></option>
					</select></td>
				</tr>
				<tr>
					<td>Mobile Number<sup>*</sup></td>
					<td><select name="mobile" id="" required="required">
							<option value="<%=mobile%>" selected><%=mobile%></option>
					</select></td>
				</tr>
				<tr>
					<td>Gender<sup>*</sup></td>
					<td><select name="gender" id="" required="required">
							<option value="<%=gen%>" selected><%=gen%></option>
					</select></td>
				</tr>
				<tr>
					<td>Aadhar Number<sup>*</sup></td>
					<td><select name="aadhar" id="" required="required">
							<option value="<%=aadhar%>" selected><%=aadhar%></option>
					</select></td>
				</tr>
				<tr>
					<td>Nationality<sup>*</sup></td>
					<td><select name="nationality" id="" required="required">
							<option value="INDIAN" selected>INDIAN</option>
					</select></td>
				</tr>

				<tr>
					<td><br> <br> <br> <input type="submit" id="sub"
						value="Create Account"></td>
				</tr>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		function check_pass() {
			var pass = document.getElementById("pass").value;
			var cpass = document.getElementById("cpass").value;
			if (pass == cpass) {
				document.getElementById("cpass").style.borderColor = "green";
				document.getElementById("sub").disabled= false;
			} else {
				document.getElementById("cpass").style.borderColor = "red";
				document.getElementById("sub").disabled= true;
			}
		}
	</script>

</body>
</html>