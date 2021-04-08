<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>personal loan</title>
    <link rel="stylesheet" href="style/bootstrap.css">
 

    <style>
        body{
            margin: 0;
            padding: 0;
            overflow: hidden;
            background-image:linear-gradient(rgba(0,0,0,0.7),rgba(0,0,0,0.5)), url("images/baground.jpg"); 
            background-attachment: fixed;
            background-size: cover;
            overflow-y: scroll;
            
        }
        ::-webkit-scrollbar{
            width: 0px;
        }   
.end{
    margin-bottom: 100px;
}
.card{
    margin-left: 375px;
    margin-top: 5px;
    margin-bottom: 100px;
    box-shadow: 20px 40px 80px rgba(0,0,0,0.9);
    transition: 0.8s;
    width: 500px;
    border:3px solid teal;
    float: left;
    background-color: #EEEEEE;
    opacity: 0.9;
}
.card:hover{
    box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
}
img{
    width: 100%;
    height: 500px;
     box-shadow:12px 15px 35px #000;
}

  .btn-success{
  width:250px;
  font-weight: 900;
  margin:10px 25px;}
  #end{
  margin-bottom:200px;}
  .btn{
  box-shadow:12px 15px 25px #000;
  padding:20px;
  font-weight: 900;
  text-shadow:2px 8px 12px #000;
  width:100%;
  font-size:22px;
 }
 .btn:hover{
  background-color:#212529;}
 .card-body{
 positin:relative;
 padding:0px;
 margin:0px;
}
@media screen and (max-width: 1366px) and (max-height: 768px) {
	.card {
		margin-top: 2px;
		margin-bottom: 100px;
		box-shadow: 20px 40px 80px rgba(0, 0, 0, 0.9);
		transition: 0.8s;
		width: 500px;
		height:470px;
		border: 3px solid teal;
		float: left;
		background-color: #EEEEEE;
		opacity: 0.9;
	}
	.btn {
		font-size: 18px;
		padding: 15px;
	}
}
    </style>
</head>
<body>
        
            <div class="card" style="width: 25rem;">
                <img src="images/wedding.gif" class="card-img-top" alt="check Balance" title="">
              
              <div class="card-body">
  	 <a href="personalloan.jsp" class="btn btn-primary" >APPLY WEDDING LOAN</a>
 </div>
 </div>
        
        <p class="end"></p>
        
</body>
</html>