<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.Statement"%>
<%@page import=" java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.SQLException"%>
<%@page import="com.bank.resource.Template"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.util.Base64"%>
<%@page import="java.time.LocalDate"%>
<%@ page import="com.bank.dto.SearchAcNumbers"%>
<%@ page import="java.util.List"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>My Activity</title>
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
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
	margin-left: 170px;
	margin-top: 5px;
	margin-bottom: 100px;
	box-shadow: 20px 40px 80px rgba(0, 0, 0, 0.9);
	transition: 0.8s;
	width: 350px;
	height: 360px;
	float: left;
	background-color: #EEEEEE;
	opacity: 0.9;
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
	margin-top: 10px;
	opacity: 0.9;
	box-shadow: 10px 15px 65px #000;
}

.imgg {
	width: 150px;
	height: 150px;
	border: 2px solid orange;
	border-radius: 10px;
}

.imgg:hover {
	width: 200px;
	height: 200px;
}

h1 {
	text-shadow: 12px 15px 25px #000;
	color: #fff;
	text-align: center;
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
	padding: 15px;
	font-weight: 900;
	text-shadow: 2px 8px 12px #000;
	width: 100%;
	font-size: 18px;
	position: relative;
}

.btn:hover {
	background-color: #212529;
}

.card-body {
	positin: relative;
	padding: 0px;
	margin: 0px;
}

.car {
	width: 25rem;
	margin: 0;
	margin-top: 20px;
	margin-left: 15px;
	padding: 0;
	display: inline-block;
	width: 150px;
	height: 200px;
	float: none;
	border: solid orange;
	background: #212527;
}

h5 {
	color: #fff;
	text-align: center;
	margin-top: 20px;
}

p {
	color: orange;
	font-size: 60px;
	text-align: center;
	margin-top: 25px;
}

td {
	color: orange;
	text-align: center;
	font-weight: 900;
	width: auto;
	font-size: 12px;
	text-shadow: 2px 8px 12px #000;
}

th {
	text-align: center;
	font-weight: 900;
	text-shadow: 2px 8px 12px #000;
}

.pri {
	text-shadow: 5px 8px 18px #000000;
	font-weight: 900;
	box-shadow: 12px 15px 25px #000;
	padding: 10px;
	margin-top: -5px;
	width: 20%;
	margin-left: 100px;
}

.search {
	box-shadow: 12px 15px 25px #000;
	width: 50%;
	padding: 10px;
}

.right {
	margin-left: 200px;
	margin-top: 150px;
	margin-bottom: 150px;
}

#deact {
	height: 310px;
}

.le {
	width: 25rem;
}

@media screen and (width: 1366px) and (max-height: 768px) {
	.pri {
		font-size: 16px;
	}
	.le {
		width: 250px;
		height: 150px;
	}
	.card {
		margin-left: 120px;
	}
	.car {
		margin-left: 5px;
		height: 150px;
	}
	p {
		font-size: 32px;
	}
	th {
		font-size: 13px;
	}
	td {
		font-size: 11px;
	}
	.imgg {
		width: 100px;
		height: 100px;
	}
	.imgg:hover {
		width: 150px;
		height: 150px;
	}
	.right {
		margin-bottom: 100px;
	}
	.card-img-top {
		width: 100%;
		height: 300px;
	}
}
</style>
</head>
<body>

	<%
	LocalDate date = java.time.LocalDate.now();
		String todaydate = date.toString();
		Connection conn = Template.getMySqlConnection();
		String sql = "select count(*) from banklog where status='ACTIVATE'";
		Statement stmnt = conn.createStatement();
		ResultSet res = stmnt.executeQuery(sql);
		int count = 0;
		while (res.next()) {
			count = res.getInt(1);
		}

		String deposite = "select sum(Amount) from abc_bank_transaction where TransactionDate=? and Cre_or_Deb='Deposite'";
		PreparedStatement pstmnt = conn.prepareStatement(deposite);
		pstmnt.setString(1, todaydate);
		ResultSet res1 = pstmnt.executeQuery();
		long dep = 0;
		while (res1.next()) {
			dep = res1.getLong(1);
		}
		String withdraw = "select sum(Amount) from abc_bank_transaction where TransactionDate=? and Cre_or_Deb='Withdraw'";
		PreparedStatement pst = conn.prepareStatement(withdraw);
		pst.setString(1, todaydate);
		ResultSet res2 = pst.executeQuery();
		long with = 0;
		while (res2.next()) {
			with = res2.getLong(1);
		}
	%>




	<div class="card car le" style="margin-left: 35px;">
		<h5>ACTIVE ACCOUNTS</h5>
		<p><%=count%></p>

	</div>


	<div class="card car" style="width: 25rem;">
		<h5>TODAY WITHDRAW</h5>
		<p><%=with%></p>

	</div>
	<div class="card car bot" style="width: 25rem;">
		<h5>TODAY DEPOSITE</h5>
		<p><%=dep%></p>

	</div>

	<div class="card cr" style="width: 25rem; margin-top: 100px;">
		<img src="images/create.gif" class="card-img-top">

		<div class="card-body">
			<a href="createcustomer.jsp" class="btn btn-primary" id="create-new">CREATE NEW
				CUSTOMER ACCOUNT</a>
		</div>
	</div>
	<form action="DisplayAllCustomers">
		<div class="card cr" style="width: 25rem; margin-top: 100px;">
			<img src="images/cus.gif" class="card-img-top" alt="check Balance"
				title="">

			<div class="card-body">
				<button type="submit" class="btn btn-primary" id="viewCustomers">VIEW
					ALL CUSTOMER DETAILS</button>

			</div>
		</div>
	</form>
	<div class="right">
		<form action="SearchCustomer">
			<button type="submit" class="btn btn-primary pri" id="searching">SEARCH
				CUSTOMER</button>
			<input type="text" id="tags"
				placeholder="Enter Account Number/ Name "
				class="form-control search" name="accnum" required="required">


		</form>
	</div>
	<div class="card" style="width: 25rem; margin-top: 0px;">
		<img src="images/update.gif" class="card-img-top" alt="update"
			title="">

		<div class="card-body">
			<a href="updatecustomer.jsp" class="btn btn-primary" id="update-cus">UPDATE
				CUSTOMER ACCOUNT DETAILS</a>
		</div>
	</div>
	<div class="card" style="width: 25rem; margin-top: 0px;">
		<img src="images/de.gif" class="card-img-top" alt="a/d" title="">

		<div class="card-body">
			<a href="a_dcustomer.jsp" class="btn btn-primary" id="active-deactive">ACTIVATE/DEACTIVATE
				CUSTOMER A/C</a>
		</div>
	</div>
	<p class="end"></p>

	<%
	SearchAcNumbers get = new SearchAcNumbers();
		List<String> allAcNumbers = get.allAcNumbersNames();
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