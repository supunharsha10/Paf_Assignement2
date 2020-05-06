<%@page import="model.Stock"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Stock Management</title>


<link rel='stylesheet prefetch' href='https://fonts.googleapis.com/css?family=Open+Sans:600'>
	  <link rel="stylesheet" href="style.css">
	  
	  <meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="home.css">
<link rel="stylesheet" href="header.css">


<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="keywords" content="footer, address, phone, icons" />
<link rel="stylesheet" href="footer.css">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

<link href="http://fonts.googleapis.com/css?family=Cookie" rel="stylesheet" type="text/css">






<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="components/jquery-3.2.1.min.js"></script>
<script src="components/main.js"></script>
</head>
<body>


     <div class="top">
      <div class="logo"><img src="images/1.png" width= 296px height=300px></div>
      <div class="name"><h2>Healthcare</h2></div>
      </div>




<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Stock Management</h1>        
				
				<form id="formStock" name="formStock" method="post" action="stock.jsp">  
					Medicine Name:  
					<input id="mname" name="mname" type="text" class="form-control form-control-sm">  
					
					<br> 
					Description:  
					<input id="description" name="description" type="text" class="form-control form-control-sm">  
					
					<br>
					 Amount:  
					 <input id="amount" name="amount" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Price:  
					 <input id="price" name="price" type="text" class="form-control form-control-sm">  
					 
				

					 
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidAppIDSave" name="hidAppIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%    
						Stock appObj = new Stock();
						out.print(appObj.readStock());   
					%>  
					
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 
	
	
	<div class="botom">
  <footer class="footer-distributed">

    <div class="footer-left">

      <h3>Health<span>Care</span></h3>

     
    </div>

    <div class="footer-center">



      <div>
        <i class="fa fa-phone"></i>
        <p>+1 555 123456</p>
      </div>

      <div>
        <i class="fa fa-envelope"></i>
        <p><a href="www.gmail.com">Healthcare10r@gmail.com</a></p>
      </div>
      <div>
          <i class="fa fa-map-marker"></i>
          <p><span>21 Revolution Street</span> Malabe, SriLanka</p>
        </div>

    </div>

    <div class="footer-right">

      <p class="footer-company-about">
        <span>About Us</span>
        This is Stock Management
      </p>
      
       <div class="footer-icons">

        <a href="#"><i class="fa fa-facebook"></i></a>
        <a href="#"><i class="fa fa-twitter"></i></a>
        <a href="#"><i class="fa fa-linkedin"></i></a>
        <a href="#"><i class="fa fa-github"></i></a>

      </div>

      

    </div>
  </div>
  </footer>

</body>

</html>