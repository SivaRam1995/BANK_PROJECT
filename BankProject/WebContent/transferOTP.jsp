<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style/bootstrap.css">
<style>
body{
margin:0;
padding:0;
background-image:linear-gradient(rgba(0,0,0,0.5),rgba(0,0,0,0.7)), url("images/baground.jpg"); 
            background-attachment: fixed;
            background-size: cover;
}
::-webkit-scrollbar{
width:0px;}
form{
background-color:#EEEEEE;
width:50%;
margin-left:300px;
padding:40px;
margin-top:100px;
margin-bottom:150px;
border:1px solid teal;
box-shadow:50px 29px 105px rgba(0,0,0,1);
border-radius:10px;
opacity:0.8;
color:#000000;
}
form:hover{
box-shadow:80px 180px 150px rgba(0,0,0,0.9);
}
.btn{
width:100%;
font-weight:900;
margin-top:40px;
}
h1{
margin-left:250px;
margin-bottom:25px;
font-weight:900;
font-size:40px;
}
.form-control{
margin-top:10px;
padding:10px;
text-align:center;
}
.form-label{
margin-top:30px;
}
.form-text{
color:red;
margin-left:100px;
margin-top:20px;}
@media screen and (max-width: 1366px) and (max-height: 768px) {
	h1 {
		margin-left: 200px;
	}
	.form-text{
	margin-left:50px;
	}
}
</style>
</head>
<body>

<form action="VerifyOTPAndTransfer" autocomplete="off">
<h1>OTP</h1>
  <div class="mb-3">
    <label class="form-label">ENTER OTP</label>
    <input type="number" class="form-control" placeholder="Enter 6 digit OTP " name="new_otp" required="required">
     <div class="form-text">OTP Already Sended To Your Email Id Check Your Mail And Enter OTP.</div>
  </div>
  
  <button type="submit" class="btn btn-primary">VERIFY</button>
  
</form>

</body>
</html>