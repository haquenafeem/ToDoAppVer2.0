<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>To-Do App UI</title>
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
  		margin-top:10px;
  		background-color:#e8e9ea;
  		border: 1px solid #e8e9ea;
  		border-radius: 10px;
  	}
  </style>
</head>
<body>

<div class="container">
 
  <div class="mid">
  <h4>Welcome : <%= request.getSession().getAttribute("username") %></h4>
 	<form method="Post" action="AddTask">
 		<input type="text" class="form-control" name="taskadd" placeholder="Add A To Do !">
 		<button type="submit" class="btn btn-primary" style="float:right;margin-top:5px;">
          <span class="glyphicon glyphicon-plus"></span>
        </button>
 	</form>
 	<div id="myDIV" style="display:none;">
 		<form method="Post" action="EditTask">
 		<input type="text" class="form-control" name="taskedit" placeholder="Edit A To Do !">
 		<button id="editvalue" type="submit" class="btn btn-primary" name="edittask" value="" style="float:right;margin-top:5px;">
          <span class="glyphicon glyphicon-plus"></span>
        </button>
 	</form>
 	</div>
   <table class="table table-striped">
    <thead>
      <tr>
        <th>Tasks</th>
      </tr>
    </thead>
     <tbody>
  	 <tr>
  	 <!-- After Successful Login This Page is Loaded -->
  <%
  	String username=request.getSession().getAttribute("username").toString();
  	
  	int trueValue=0; //keeping track of the tasks that have been completed
  	int totalValue=0; //total tasks user have
  	//out.println(username);
  	try{
  		//connecting to database and getting information about tasks from the database
  		Class.forName("com.mysql.jdbc.Driver");
  		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp","root","");
  		String queryCheck="SELECT * FROM tasklist WHERE username ="+"'"+username+"'";
  		Statement stmnt = conn.createStatement();
        ResultSet rset = stmnt.executeQuery(queryCheck); 
        //out.println("<br>");
        while(rset.next()){
        		String check=rset.getString("report").toString();//getting value of whether task completed or not
        		String id=rset.getString("id").toString();//getting the id 
        		//System.out.println(id);
        		if(check.equals("true")){//checking if task has been done
        			trueValue++;
        			totalValue++;
        			out.println("<td style=\"text-decoration: line-through;\">"+rset.getString("task")+//getting task name or to do name, this block creates three button for check, edit and delete to do
                			"<form method=\"Post\" action=\"Delete\"><button type=\"submit\" class=\"btn btn-danger\" name=\"delete\" value=\""+id+"\"style=\"float:right;margin-right:3px;\">"+
                			"<span class=\"glyphicon glyphicon-remove\"></span></button></form>"+"<button onClick=\"edit(this.value)\" value=\""+id+"\" class=\"btn btn-info\" style=\"float:right;margin-right:3px;\"><span class=\"glyphicon glyphicon-pencil\"></span></button>"+
                        					"<form style=\"float:right;\"><button type=\"submit\" class=\"btn btn-success\" style=\"float:right;margin-right:3px;\" disabled>"+
                                			"<span class=\"glyphicon glyphicon-ok\"></span></button>"+"</td></form>"+
                			"</tr>");
        		}else{//checking if task has not been done
        			totalValue++;
        			out.println("<td style=\"text-decoration: none;\">"+rset.getString("task")+//getting task name or to do name, this block creates three button for check, edit and delete to do
                			"<form method=\"Post\" action=\"Delete\"><button type=\"submit\" class=\"btn btn-danger\" name=\"delete\" value=\""+id+"\"style=\"float:right;margin-right:3px;\">"+
                			"<span class=\"glyphicon glyphicon-remove\"></span></button></form>"+"<button onClick=\"edit(this.value)\" value=\""+id+"\" class=\"btn btn-info\" style=\"float:right;margin-right:3px;\"><span class=\"glyphicon glyphicon-pencil\"></span></button>"+        
                        					"<form style=\"float:right;\" method=\"Post\" action=\"TaskDone\"><button type=\"submit\" class=\"btn btn-success\" name=\"check\" value=\""+id+"\" style=\"float:right;margin-right:3px;\">"+
                                			"<span class=\"glyphicon glyphicon-ok\"></span></button></form>"+"</td>"+
                			"</tr>");
        		}
        	
        }
        
  	}catch(Exception e){
  		e.printStackTrace();
  	}
  %>
  	 </tr>
  	 </tbody>
    </table>
    <%
    //calulating the percentage of tasks done or to do done
    double percentage=((double)trueValue/(double)totalValue)*100;
    System.out.println(percentage);
    %>
    <!-- Setting Up the Progress Bar According To The Percentage Calculation -->
   <div class="progress">
    <div class="progress-bar progress-bar-info progress-bar-striped" role="progressbar" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100" <% out.print("style=\"width:"+percentage+"%;\""); %>>
      <%= trueValue %> complete out of <%= totalValue  %>
    </div>
  </div>
  <!-- Logout process Submitted To Logout Servlet -->
  <form method="Post" action="Logout">
    <br>
    <br>
    <div class="text-right">
    <button type="submit" class="btn btn-info">Log-Out</button>
	</div>
  </form>
</div>
  </div>
  <!-- Script for Editing To Do  -->
 <script>
function edit(val) {
 // alert(val);
 var y= document.getElementById("editvalue");
  var x = document.getElementById("myDIV");
  if (x.style.display === "none") {
    x.style.display = "block";
    y.value=val;
  } else {
    x.style.display = "none";
    y.value="something";
  }
}
</script>
  </body>
  </html>