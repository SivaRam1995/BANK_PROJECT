<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="style/bootstrap.css">

    <style>
        body{
            margin: 0;
            padding: 0;
            overflow: hidden;
        }
         .right{
            float: left;
            width: 700px;
            height: 500px;
            background-color: white;
        }
        form{
            width: 700px;

        }
        table{
            width: 600px;
            height: 100%;
        }
        
        input[type="text"],input[type="password"]{
            padding: 15px;
            width: 100%;
            margin-left: 30px;
            margin-top: 10px;
            border:none;
            border-bottom: 1px solid #000;
            outline: none;

        }
        input[type="password"]:focus{
            border-bottom: 2px solid green;
        }
        input[type="text"]:focus{
            border-bottom: 2px solid green;

            
        }
        input[type="text"]
        {
            margin-top: 60px;
            
        }
        input[type="submit"]{
            width: 150px;
            padding: 10px;
            margin-top: 50px;
            margin-left: 250px;
            color: #f6f6f6;
            font-size: 14px;
            font-weight: 900;
            background-color: green;
            border:1px solid green;
            box-shadow: 2px 3px 8px #000;
            cursor: pointer;
    

        }
        input[type="submit"]:hover{
            background: #004d00;
        }
        h1{
            margin-top: 50px;
            font-size: 35px;
            font-weight: 900;
            margin-bottom: 70px;
            text-align:center;
        }
       
        
    </style>
</head>
<body oncontextmenu="return false;">
    <div class="right">
    <h1>CREATE ABC ONLINE BANKING A/C</h1>
        <form action="OnlineReg" autocomplete="off">
            <table>
                
                <tr><td>
            <input type="text" placeholder="ENTER YOUR ACCOUNT NUMBER" name="accnum">
        </td></tr>
        <tr><td>
            <input type="submit" value="CONTINUE">
        </td></tr>
        </table>
        </form>
    </div>
</body>
</html>