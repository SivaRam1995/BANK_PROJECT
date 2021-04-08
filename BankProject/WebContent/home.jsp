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
body{
margin:0;
background-image:linear-gradient(rgba(0,0,0,0.6),rgba(0,0,0,0.6)), url("images/baground.jpg"); 
            background-size: cover;
            background-attachment: fixed;
            opacity:0.9;
}
h1{
margin-top:4%;
color:orange;
font-size:50px;
text-align:center;
text-shadow: 5px 7px 10px #000;
font-family: cursive;
}
#date{
margin-top:40px;
color:orange;
margin-left:950px;
font-weight: 900;
font-family:cursive;
text-shadow:2px 5px 8px #000;
font-size:30px;
}
img{
margin-top:-30px;
width:250px;
height:250px;
border-radius:20px;
border:3px solid orange;
margin-left:530px;
box-shadow:18px 20px 145px rgba(0,0,0,0.9); 
opacity:1;}
img:hover{
border:6px solid orange;
transition:0.2s;}
@media screen and (max-width: 1366px) and (max-height: 768px)
{
#date{
color:orange;
margin-left:900px;
font-weight: 900;
font-family:cursive;
text-shadow:2px 5px 8px #000;
font-size:30px;
}
img{
margin-left:500px;
width:300px;
height:300px;}
h1{
margin-top:1%;
font-size:38px;}
}
</style>
</head>
<body>
<%
Connection conn = Template.getMySqlConnection();
String output = null;
String name=(String)session.getAttribute("logid");
String pass=(String)session.getAttribute("pass");
String sql="select userimage from banklog where loginid=? and password=?";
PreparedStatement pstmnt = conn.prepareStatement(sql);
pstmnt.setString(1, name);
pstmnt.setString(2, pass);
ResultSet res = pstmnt.executeQuery();

String profile=null;

while(res.next())
{
Blob blob = res.getBlob(1);
if(blob!=null) {
	
	int len=(int)blob.length();
	byte[] imgBytes=blob.getBytes(1L, len);
	String encodedImg=Base64.getEncoder().encodeToString(imgBytes);
	profile="data:image/png;base64,"+encodedImg;
}
}

 %>

<p id="date">Date:
<script> document.write(new Date().toLocaleDateString()); </script>
</p>
 <a href="myprofile.jsp" title="click To See My Profile"><img src="<%=profile%>"></a>
<h1>WELCOME <br><br>TO<br><br>ABC BANK ONLINE SYSTEM</h1>


</body>
</html>