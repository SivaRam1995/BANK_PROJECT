<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="style/bootstrap.css">
    <style>
        body{ margin: 0;
            padding: 0;
        }
        ::-webkit-scrollbar{
            width: 0px;
        }
         .right{
            float: left;
            width: 700px;
            height: 500px;
            opacity:0.9;
        }
        form{
            width: 700px;
            padding: 20px;
            opacity:0.9;

        }
        table,tr,td{
        margin-left:200px;
            width: 400px;
            height: 100%;
            text-shadow:5px 12px 30px rgba(0,0,0,0.4);
        }
        
        input[type="text"],input[type="password"],input[type="email"],input[type="number"],input[type="date"], select{
            padding: 15px;
            width: 100%;
            margin-top: 10px;
            border:none;
            border-bottom: 1px solid #000;
            outline: none;

        }
        input[type="password"]:focus{
            border-bottom: 2px solid green;
        }
        input[type="email"]:focus, [type="text"]:focus, input[type="date"]:focus{
            border-bottom: 2px solid green;
   
        }
        
        input[type="submit"]{
            width: 400px;
            padding: 10px;
            margin-top: 50px;
            margin-left: 220px;
            color: #f6f6f6;
            font-size: 14px;
            font-weight: 900;
            background-color: green;
            border:1px solid #fff;
            box-shadow: 2px 3px 8px rgba(0,0,0,0.9);
            cursor: pointer;
            margin-bottom: 100px;
            font-weight:900;
            text-shadow:2px 3px 8px rgba(0,0,0,0.9);
    

        }
        input[type="submit"]:hover{
            background: #004d00;
        }
        h1{
            margin-top: 50px;
            font-size: 40px;
            font-weight: 900;
            margin-left:250px;
            color:orange;
        }
        
       #image{
           display: block;
           padding: 8px;
           width: 250px;
           margin-left: 150px;
       }
       select{
           width: 100%;
           padding: 10px;
           width: 250px;
           margin-left: -0px;
       }
       #a{
           margin-top: 100px;
       }
       sup{
       color:red;}

   
    </style>
</head>
<body>
  <h1>CREATE AN CUSTOMER ACCOUNT</h1>
    <div class="right">
        <form action="CustomerRegister" method="post" enctype="multipart/form-data">
            <table>
                
                <tr>
                    <td><hr style="width:100%;margin-left:125px;"><br>
                        <br><br>
                        <b>Bank Details</b><hr></td>
                </tr>
               
                <tr>
                    <td>Bank Name<sup>*</sup></td>
                    <td><select name="bankname" id="bank-name" required="required">
                        <option value="ABC BANK" selected>ABC BANK</option>
                    </select></td>
                </tr>
                <tr>
                    <td>Type Of Account<sup>*</sup></td>
                    <td><select name="acctype" id="acc-type" required="required" title="PLEASE SELECT ACCOUNT TYPE">
                        <option value="">---SELECT---</option>
                        <option value="CURRENT ACCOUNT">CURRENT ACCOUNT</option>
                        <option value="SAVINGS ACCOUNT">SAVINGS ACCOUNT</option>
                    </select></td>
                </tr>
       

                <tr>
                    <td id="a"><br><b>User Details</b><hr></td>
                </tr>
                <tr>
                    <td id="image">  <img id="output_image" height=250px width=280px title="upload your image" >
                        <input type="file" accept="image/*" onchange="preview_image(event)" required="required" name="userpic" id="cus-pic">
                        </td>
                </tr>
                

                <tr>
                    <td>First Name<sup>*</sup></td>
                    <td>
                    <input type="text" placeholder="Enter First Name" title="ENTER FIRST NAME" name="fname" id="f-name" required="required">
                </td></tr>
                <tr>
                    <td>Last Name<sup>*</sup></td>
                    <td>
                    <input type="text" placeholder="Enter Last Name" title="ENTER LAST NAME" name="lname" id="l-name">
                </td></tr>
                <tr>
                    <td>Email Id<sup>*</sup></td>
                    <td>
                    <input type="email" placeholder="Valid Email to get OTP" required="required" title="ENTER A VALID EMAIL ID" name="email" id="cus-email">
                </td></tr>
                <tr>
                    <td id="a"><br><br><b>Personal Details</b><hr></td>
                </tr>
                <tr>
                    <td>Date Of Birth<sup>*</sup></td>
                    <td>
                    <input type="date" placeholder="YYYY-MM-DD" min="1900-01-01" max="3000-01-01" name="dob" id="cus-dob" required="required">
                </td></tr>
                <tr>
                    <td>Mobile Number<sup>*</sup></td>
                    <td>
                    <input type="number" placeholder="Enter Mobile Numebr"  pattern="/^[6-9][0-9]{0,8}$/" onKeyPress="if(this.value.length==10) return false;" title="NUMBER STARTS WITH 6 or 7 or 8 or 9" name="mobile" id="mobile-num" required="required">
                </td></tr>
                <tr>
                    <td>Gender<sup>*</sup></td>
                    <td><select name="gender" id="gender" required="required">
                        <option value="">---SELECT---</option>
                        <option value="MALE">MALE</option>
                        <option value="FEMALE">FEMALE</option>
                        <option value="OTHER">OTHER</option>
                    </select></td>    
                </tr>
                <tr>
                    <td>Aadhar Number<sup>*</sup></td>
                    <td>
                    <input type="number" placeholder="Enter Aadhar Number" pattern="/^-?\d+\.?\d*$/"
						onKeyPress="if(this.value.length==12) return false;" title="ENTER 12 DIGIT AADHAR NUMBER" name="aadhar" maxLength="19" id="aadhar-num" required="required" >
                </td></tr>
                <tr>
                    <td>Nationality<sup>*</sup></td>
                     <td><select name="nationality" id="nationality" required="required">
                        <option value="INDIAN" selected>INDIAN</option>
                    </select></td></tr>

                <tr><td><br><br><br>
               
            <input type="submit"  value="CREATE CUSTOMER ACCOUNT" id="submit-cus">
        </td></tr>
        </table>
        </form>
    </div>


<script type="text/javascript">
    function preview_image(event) {
      var reader = new FileReader();
      reader.onload = function(){
        var output = document.getElementById('output_image');
        output.src = reader.result;
      }
      reader.readAsDataURL(event.target.files[0]);
    }
  </script>
  
</body>
</html>