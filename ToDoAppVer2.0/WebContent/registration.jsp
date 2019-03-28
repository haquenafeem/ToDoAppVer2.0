<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>To-Do App Registration</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
  <style>
  	.mid{
  		width:50%;
  		padding:5%;
  		margin:auto;
  		margin-top:200px;
  		background-color:#e8e9ea;
  		border: 1px solid #e8e9ea;
  		border-radius: 10px;
  	}
  </style>
</head>
<body>

<div class="container">
 
  <div class="mid">
  <!-- Submitting new User Information To RegistrationProcess -->
  <form method="Post" action="RegistrationProcess">
  	<div class="input-group">
      
      <input id="email" type="text" class="form-control" name="username" placeholder="UserName">
    </div>
     <br>
    <div class="input-group">
      
      <input id="email" type="text" class="form-control" name="email" placeholder="Email">
    </div>
    <br>
   
    <div class="input-group">
      
      <input id="password" type="password" class="form-control" name="password" placeholder="Password">
    </div>
    <br>
    <div class="text-left">
  	<button type="submit" class="btn btn-danger">Sign-Up</button>
	</div>
  </form>
  <br>
  	<p>Already have an account ? <a href="index.jsp">Login</a> </p>
  </div>
  </div>
  </body>
  </html>