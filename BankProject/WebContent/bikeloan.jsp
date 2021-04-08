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

form {
	float: left;
	background-color: #EEEEEE;
	width: 40%;
	margin-left: 120px;
	padding: 20px;
	margin-top: 20px;
	margin-bottom: 150px;
	border: 1px solid teal;
	box-shadow: 50px 29px 105px rgba(0, 0, 0, 0.9);
	border-radius: 10px;
	opacity: 0.9;
	color: #000000;
}

::-webkit-scrollbar {
	width: 0px;
}

::placeholder {
	text-align: center;
}

form:hover {
	box-shadow: 80px 180px 150px rgba(0, 0, 0, 0.9);
}

.btn {
	width: 40%;
	font-weight: 900;
	margin: 15px;
	margin-top: 40px;
	box-shadow: 12px 18px 45px #000;
}

h1 {
	text-align: center;
	margin-bottom: 25px;
	font-weight: 900;
	text-shadow: 4px 13px 15px rgba(0, 0, 0, 0.5);
	color: #007bff;
}

.form-control {
	margin-top: 10px;
	padding: 10px;
	text-align: center;
}

.form-label {
	margin-top: 30px;
	font-weight: 500;
}

#form1 {
	height: 610px;
	margin-left: 15px;
}

.topp {
	margin-top: 105px;
}

#bike {
	width: 85%;
	margin-left: 100px;
}

.heading1 {
	margin-top: 60px;
	width: 48%;
	margin-left: 18px;
	height: 600px;
}

h2, h3, h4 {
	text-shadow: 12px 15px 20px rgba(0, 0, 0, 0.4);
}

.split {
	border-radius: 15px;
	box-shadow: 2px 8px 18px rgba(0, 0, 0, 0.5);
	padding: 10px;
}

@media screen and (max-width: 1366px) and (max-height: 768px) {
	.heading1 {
		margin-top: 15px;
	}
}
</style>
</head>
<body>

	<form class="heading1">
		<h1>BIKE LOAN</h1>
		<hr>
		<br> <br>
		<h2 style="margin-top: -15px;">ELIGIBILITY</h2>
		<br>
		<h3>Salaried/ Self employed:</h3>
		<br>
		<ul>
			<li>Age - Minimum- 21 Years and Maximum- 60 Years at loan
				maturity</li>

			<li>Loan Amount - Minimum- Rs. 25,000 and Maximum- Rs. 3,00,000</li>

			<li>Tenor - Minimum- 6 months and Maximum- 60 months</li>

			<li>Residential Stability - Minimum 1 Year</li>

			<li>Employment Stability - Minimum 1 Year</li>

			<li>Income - Not mandatory</li>

			<li>Loan To Value (LTV) - Up to 95%<br> <br>
			</li>
		</ul>
		<ul>
			<li><a href="#EMI">Calculate EMI</a></li>
			<li><a href="#bike">Apply Loan</a></li>
		</ul>
	</form>
	<form class="heading1 hd">
		<div class="split">
			<h4>What is a Two Wheeler Loan?</h4>
			<br>
			<p>A two wheeler loan is an easy and hassle-free way to finance
				the purchase of a two wheeler (bike or scooty) of your choice. The
				loan amount along with the applicable interest amount can be repaid
				in the form of Equal Monthly Instalments or EMIs over a fixed loan
				tenure. Bike loans are available for both salaried and self-employed
				individuals.</p>
		</div>
		<br>
		<div class="split">
			<h4>Steps to Take a Two Wheeler Loan</h4>
			<br> The following are the steps to get two wheeler loan
			<ul>
				<li><b>Simple Online Application :</b> Apply through our
					website by filling in a two wheeler loan application and submitting
					it online.</li>
				<li><b>Minimal Documentation :</b> Furnish the required two
					wheeler loan documents based on whether you are applying for a
					standard or a super bike loan.</li>
				<li><b>Verification :</b> We will verify and validate your
					documents and credit report.</li>
				<li><b>Approval :</b> If there are no discrepancies found, your
					bike loan will be sanctioned and the loan principal will be
					disbursed to your bike dealer.</li>
			</ul>
		</div>
	</form>

	<form id="EMI">
		<h1>EMI CALCULATOR</h1>
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">LOAN
				AMOUNT</label> <input type="number" class="form-control" name="lm" id=loan1
				aria-describedby="emailHelp" placeholder="Enter Loan Amount"
				required="required">
		</div>
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">REPAYMENT
				IN MONTHS</label> <input type="number" class="form-control" name="rim"
				id=months1 aria-describedby="emailHelp"
				placeholder="Enter In Months" required="required">
		</div>
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">INTREST
				RATE</label> <input type="number" class="form-control" name="ir" id=rate1
				onchange=emi(); disabled value="7.5" required="required">
		</div>

		<button type=button onclick='emi()' class="btn btn-primary">CALCULATE
			EMI</button>
		<button type=reset class="btn btn-primary">RESET</button>
	</form>
	<form id="form1">
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label topp">EMI</label> <input
				type="number" class="form-control" name="lm" id=pay1
				aria-describedby="emailHelp" disabled placeholder="Monthly EMI"
				required="required">
		</div>
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">INTREST
				PAYABLE</label> <input type="number" class="form-control" name="rim"
				id=tintt1 aria-describedby="emailHelp" placeholder="Intrest"
				disabled required="required">
		</div>
		<div class="mb-3">
			<label for="exampleInputEmail1" class="form-label">TOTAL
				PAYABLE</label> <input type="number" class="form-control" name="ir" id=gt1
				aria-describedby="emailHelp" placeholder="Total Payable Amount"
				disabled required="required">
		</div>
	</form>

	<form action="BikeLoan" id="bike">
		<h1>BIKE LOAN FORM</h1>
		<div class="mb-3">
			<label class="form-label">FULL NAME</label> <input type="text"
				class="form-control" name="name" aria-describedby="emailHelp"
				placeholder="Enter Full Name" required="required">
			<div id="emailHelp" class="form-text">Name(As Per PAN CARD).</div>
		</div>
		<div class="mb-3">
			<label class="form-label">EMAIL ID</label> <input type="email"
				class="form-control" name="emailid" aria-describedby="emailHelp"
				placeholder="Enter Email Id " required="required">
			<div id="emailHelp" class="form-text">Enter a valid email id
				after applying loan details sends to your mail.</div>
		</div>
		<div class="mb-3">
			<label class="form-label">MOBILE NUMBER</label> <input type="number"
				class="form-control le" name="mobile" aria-describedby="emailHelp"
				placeholder="Enter Mobile Number" required="required">
		</div>
		<div class="mb-3">
			<label class="form-label">CITY OF RESIDENCY</label> <select
				class="form-select form-control" name="city">
				<option>SELECT</option>
				<option value="Kadapa">KADAPA</option>
				<option value="Hyderabad">HYDERABAD</option>
				<option value="Banglore">BANGLORE</option>
				<option value="Chennai">CHENNAI</option>
			</select>
		</div>

		<div class="mb-3">
			<label class="form-label">DATE OF BIRTH</label> <input type="date"
				class="form-control" name="dob" placeholder="Enter Date Of Birth"
				required="required">
		</div>
		<div class="mb-3">
			<label class="form-label">PAN CARD NUMBER</label> <input
				type="number" class="form-control le" name="pan"
				placeholder="Enter PanCard Number" required="required">
		</div>
		<div class="mb-3">
			<label class="form-label">REQUIRED LOAN AMOUNT</label> <input
				type="number" class="form-control le" name="loanamt"
				placeholder="Enter Required Loan Amount" required="required">
		</div>

		<div class="mb-3">
			<label class="form-label">OCCUPATION</label> <select
				class="form-select form-control" name="occupation">
				<option selected>SELECT</option>
				<option value="Salaried">SALARIED</option>
				<option value="Self Employeed">SELF EMPLOYEED</option>
				<option value="Other">OTHER</option>
			</select>
		</div>
		<div class="mb-3">
			<label class="form-label">ENTER YOUR MONTHLY INCOME</label> <input
				type="number" class="form-control le" name="income"
				placeholder="Enter Your Monthly Income Amount In Rupees"
				required="required">
		</div>

		<div class="mb-3">
			<label class="form-label">VEICHEL MANUFACTURE</label> <select
				class="form-select form-control" name="manufacture">
				<option>SELECT</option>
				<option value="Bajaj">BAJAJ</option>
				<option value="Honda">HONDA</option>
				<option value="Royal Enfield">ROYAL ENFIELD</option>
				<option value="Tvs">TVS</option>
				<option value="Yamaha">YAMAHA</option>
			</select>
		</div>

		<div class="mb-3">
			<label class="form-label">VEICHEL MODEL</label> <input type="text"
				class="form-control le" name="model"
				placeholder="Enter Veichel Model" required="required">
		</div>
		<div class="mb-3">
			<label class="form-label">VEICHEL VARIENT</label> <select
				class="form-select form-control" name="variant">
				<option selected>SELECT</option>
				<option value="Standard">STANDARD</option>
				<option value="Bsvi Standard">BSVI STANDARD</option>
			</select>
		</div>
		<div class="mb-3">
			<label class="form-label">Tenor</label> <select
				class="form-select form-control" name="tenor">
				<option value="12">SELECT MONTHS(By Default Select 12)</option>
				<option value="24">24 MONTHS</option>
				<option value="36">36 MONTHS</option>
				<option value="48">48 MONTHS</option>
				<option value="60">60 MONTHS</option>
			</select>
		</div>

		<button type="submit" class="btn btn-primary" style="width: 97%;">APPLY</button>

	</form>

	<script>
	function emi()
	{
		if(document.getElementById('loan1').value==null || document.getElementById('loan1').value.length==0 || document.getElementById('months1').value==null || document.getElementById('months1').value.length==0 || document.getElementById('rate1').value==null || document.getElementById('rate1').value.length==0 ) 
		{
			document.getElementById('pay1').value='Data Reqd.'
			}
		else 
		{
			var pay1='';
			var princ1= document.getElementById('loan1').value;
			var term1= document.getElementById('months1').value;
			var intr1=document.getElementById('rate1').value / 1200;
			document.getElementById('pay1').value =Math.round(princ1 * intr1 / (1-(Math.pow(1/(1 + intr1), term1)))*100)/100; 
			document.getElementById('gt1').value= Math.round((document.getElementById('pay1').value * document.getElementById('months1').value)*100)/100;
			document.getElementById('tintt1').value=Math.round((document.getElementById('gt1').value*1 - document.getElementById('loan1').value*1)*100)/100;
			}
		}
</script>

</body>
</html>