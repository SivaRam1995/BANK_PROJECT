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
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>ABC BANK</title>
<link rel="icon" href="images/logo.jpg">
<style>
body {
	margin: 0;
	padding: 0;
	background-color: rgba(0, 0, 0, 0.9);
}

::-webkit-scrollbar {
	width: 0;
}

.container {
	width: 100%;
	height: 740px;
	box-shadow: 15px 25px 48px rgba(0, 0.4, 0.2, 0.8);
	border-bottom: 0px solid #000;
}

.left {
	float: left;
	background-color: #212529;
	opacity: 0.9;
	width: 14.77%;
	height: 740px;
	box-shadow: 2px 85px 102px rgba(0.2, 0.2, 0, 1);
	border-right: 0px solid #000;
}

a {
	display: block;
	text-decoration: none;
	padding: 18px;
	margin-top: 20px;
	color: white;
	font-size: 900;
	font-size: 18px;
	text-shadow: 12px 13px 25px #000;
}

a:hover {
	background-color: rgba(0, 0, 0, 0.9);
	font-size: 20px;
}

.right {
	float: left;
	width: 85%;
	height: 740px;
	box-shadow: -2px -85px -182px rgba(0.2, 0.2, 0, 0.1);
}

img {
	width: 100%;
	height: 150px;
	background: rgba(0, 0.4, 0.2, 0.7);
	box-shadow: 2px 5px 15px rgba(0, 0.4, 0.2, 0.8);
	border-bottom: 2px solid orange;
	border-radius: 3px;
}

img:hover {
	background: none;
}

caption {
	margin-top: 50px;
	font-size: 40px;
}

iframe {
	width: 100%;
	height: 100%;
	border: none;
}

h2 {
	color: #f6f6f6;
	text-align: center;
	text-shadow: 2px 5px 8px rgba(0, 0.4, 0.2, 0.8);
	margin-top: -33px;
	margin-left: 65px;
	color: orange;
}

hr {
	width: 60%;
}

span {
	color: white;
	text-shadow: 2px 3px 4px rgba(0.2, 0.2, 0, 0.5);
	text-align: center;
	font-size: 25px;
	font-family: cursive;
}

#demo {
	color: white;
	text-shadow: 2px 3px 4px rgba(0.2, 0.2, 0, 0.5);
	width: 90%;
	text-align: left;
	font-size: 14px;
	font-weight: 800;
	background-color: rgba(0, 0, 0, 0.9);
	border-radius: 15px;
	padding: 12px;
}

h1 {
	text-align: center;
	color: #FFFFFF;
	font-weight: 900;
	font-family: 'Courier New', Courier, monospace;
	text-shadow: 2px 5px 10px #000;
	font-size: 49px;
}

#logout {
	background: none;
	border: none;
	color: #fff;
	cursor: pointer;
	margin-top: 45px;
	padding: 25px;
	font-family: cursive;
	font-size: 25px;
}

#logout:hover {
	text-shadow: 25px 20px 48px #000;
	background: none;
		color: red;
	}
	#logout:focus {
		outline: 0 !important;
	}

#a {
	margin-left: 5px;
	font-weight: 900;
	font-family: cursive;
	font-size: 20px;
	text-shadow: 2px 8px 12px #000;
}

#mynamee {
	display: inline;
	background: none;
	font-size: 20px;
	padding: 0;
	margin: 0;
}

#mynamee:hover {
	color: orange;
}

@media screen and (max-width: 1366px) and (max-height: 768px) {
	.container {
		width: 100%;
	}
	.left {
		height: 640px;
	}
	.right {
		height: 640px;
	}
	a {
		font-size: 16px;
		padding: 12.5px;
	}
	a:hover {
		font-size: 17px;
	}
	#logout {
		margin-top: 44px;
	}
	#logout:hover {
		background: none;
		color: red;
	}
	#logout:focus {
		outline: 0 !important;
	}
	img {
		width: 100%;
		height: 142px;
		background: rgba(0, 0.4, 0.2, 0.7);
		box-shadow: 2px 5px 15px rgba(0, 0.4, 0.2, 0.8);
		border-bottom: 2px solid orange;
		border-radius: 3px;
	}
	h2 {
		color: #f6f6f6;
		text-align: center;
		text-shadow: 2px 5px 8px rgba(0, 0.4, 0.2, 0.8);
		margin-top: -30px;
		margin-left: 65px;
		font-size: 22px;
		font-weight: 900;
		color: orange;
	}
}
</style>
</head>
<body>


	<%
	response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("pragma", "no-cache");
		response.setDateHeader("Expire", 0);
		Connection conn = Template.getMySqlConnection();
		String output = null;
		String name = (String) session.getAttribute("logid");
		String pass = (String) session.getAttribute("pass");
		String sql = "select empname from emplogin where loginid=? and password=?";
		PreparedStatement pstmnt = conn.prepareStatement(sql);
		pstmnt.setString(1, name);
		pstmnt.setString(2, pass);
		ResultSet res = pstmnt.executeQuery();
		while (res.next()) {
			output = res.getString(1);
		}
		if (output == null) {
			response.sendRedirect("index.html");
		}
	%>


	<div class="container">
		<div class="left">
			<img src="images/ABCLOGO.png" alt="">
			<h2>ABC BANK</h2>


			<span id="a"> <br>Hello, <b style="color: orange;"><%=output%></b>.!
			</span>
			<hr>
			<a href="emphome.jsp" target="logframe" id="home">HOME</a> <a
				href="myactivity.jsp" target="logframe" id="activity">MY ACTIVITIES</a> <a
				href="checkbal.jsp" target="logframe" id="getbal">CHECK BALANCE</a> <a
				href="deposite.jsp" target="logframe" id="deposited">DEPOSITE</a> <a
				href="withdraw.jsp" target="logframe" id="withdrawn">WITHDRAW</a> <a
				href="ministatement.jsp" target="logframe" id="ministmnt">MINI STATEMENT</a>
			<!-- <a href="index.html" target="_top" id="logout">LOGOUT</a>
				 -->
			<form action="LogOut">
				<button type="submit" id="logout">LOGOUT</button>
			</form>

		</div>
		<div class="right">
			<iframe src="emphome.jsp" name="logframe"></iframe>
			<h1>THANK YOU</h1>
		</div>

	</div>
</body>
</html>