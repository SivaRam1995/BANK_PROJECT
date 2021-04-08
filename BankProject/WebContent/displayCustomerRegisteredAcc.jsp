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
	border-color: orange;
}

b {
	margin-left: 200px;
	font-size: 18px;
	text-shadow: 12px 15px 20px #000;
}

#or {
	color: orange;
}
.btn{
width:20%;
margin-bottom:5px;
float:left;
}
#btnExport{
float:right;}

@media screen and (max-width: 1366px) and (max-height: 768px) {
	img {
		margin-left: 270px;
	}
}
</style>
</head>
<body>

	<%
	String bankname = (String) session.getAttribute("newbankname");
		String acctype = (String) session.getAttribute("newtypeacc");
		String accnum = (String) session.getAttribute("newaccnum");
		String profile = null;
		String username = (String) session.getAttribute("newname");
		String email = (String) session.getAttribute("newemail");
		String dob = (String) session.getAttribute("newdob");
		String mobile = (String) session.getAttribute("newmobile");
		String gen = (String) session.getAttribute("newgender");
		String aadhar = (String) session.getAttribute("newaadhar");
		String nationality = (String) session.getAttribute("newnationality");

		Connection conn = Template.getMySqlConnection();
		String sql = "select userimage from banklog where aadhar=?";
		PreparedStatement pstmnt = conn.prepareStatement(sql);
		pstmnt.setString(1, aadhar);
		ResultSet res = pstmnt.executeQuery();
		while (res.next()) {
			Blob blob = res.getBlob(1);
			if (blob != null) {

				int len = (int) blob.length();
				byte[] imgBytes = blob.getBytes(1L, len);
				String encodedImg = Base64.getEncoder().encodeToString(imgBytes);
				profile = "data:image/png;base64," + encodedImg;
			}
		}
	%>
	<input type="button" value="PRINT" class="btn btn-primary"
		onClick="window.print()">
	
	<form action="sendCustomerAccMail">
		<input type="submit" id="btnExport" class='btn btn-success'
			value="SEND TO MAIL" onclick="Export()" />
			</form>
	
	
	<table class="table table-bordered table-dark table-striped"
		id="ReportTable">
		<tr>
			<td colspan="2"><img src="<%=profile%>" style="width: 450px; height: 450px;"></td>
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
			<td><b>My Name</b></td>
			<td><b id="or"><%=username%></b></td>
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

	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.22/pdfmake.min.js"></script>
	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/html2canvas/0.4.1/html2canvas.min.js"></script>
	<script type="text/javascript">
		var i = 0;
		function Export() {
			i = i + 1;
			html2canvas(document.getElementById('ReportTable'), {
				onrendered : function(canvas) {
					var data = canvas.toDataURL();
					var docDefinition = {
						content : [ {
							image : data,
							width : 500
						} ]
					};
					pdfMake.createPdf(docDefinition).download(<%=aadhar%>+".pdf");
				}
			});
		}
	</script>

</body>
</html>