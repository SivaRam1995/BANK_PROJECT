<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>otp</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
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
        
        input[type="number"]{
            padding: 15px;
            width: 100%;
            margin-left: 30px;
            margin-top: 10px;
            border:none;
            border-bottom: 1px solid #000;
            text-align:center;
            outline: none;
            margin-top: 100px;

        }
         input[type="number"]:focus{
          border-bottom: 5px solid green;
         }
         input[type="number"]:focus > p{
         visibility:none;
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
        caption{
            margin-top: 50px;
            font-size: 40px;
            font-weight: 900;
        }
        
    </style>
</head>
<body>
    <div class="right">
        <form action="VerifyOnlineReg" method="post" autocomplete="off">
            <table>
                <caption>Enter OTP</caption>
                <tr><td>
            <input type="number" placeholder="ENTER 6 DIGIT OTP " name="otp"  pattern="/^-?\d+\.?\d*$/" onKeyPress="if(this.value.length==6) return false;">
        </td></tr>
        <tr><td><p style="color:red;margin-left:120px">OTP Already Sended To Your Email Id Check Your Mail And Enter OTP</p></td></tr>
        <tr><td>
            <input type="submit" value="Verify">
        </td></tr>
        </table>
        </form>
    </div>
</body>
</html>