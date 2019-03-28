

import java.io.IOException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginCheck
 */
@WebServlet("/LoginCheck")
public class LoginCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static String name="";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at:12 ").append(request.getContextPath());
		
		/*PrintWriter out=response.getWriter();
		out.println("<html><body>");
		out.println("Logged Out Successfully !");
		out.println("Continue to Log-In page");
		out.println("<a href=\"index.jsp\">Log-In</a>");
		out.println("</body></html>");*/
		
		//Logout 
		request.getSession().invalidate();
		request.getSession().setAttribute("username", null);
		request.getRequestDispatcher("index.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html");
		//PrintWriter out=response.getWriter();
		
		// Taking the login information
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try {
			
			// Connecting to database
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp","root","");

            // checking database whether the user name and password is in there
            String queryCheck = "SELECT * from userinfo WHERE email = " +"'" +email+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(queryCheck); 
            
            String queryCheck2 = "SELECT * from userinfo WHERE password = " +"'" +password+"'";
            Statement st2 = conn.createStatement();
            ResultSet rs2 = st2.executeQuery(queryCheck2);
            // if this ID already exists
            if(rs.absolute(1) && rs2.absolute(1)) {
            	
                /* out.println("the result is "+ rs.getString("username"));
            	out.println("Successfull");*/
            	name=rs.getString("username");
            	//Creating a session and passing it to app.jsp file
            	request.getSession().setAttribute("username",rs.getString("username"));
            	request.getRequestDispatcher("app.jsp").forward(request, response);
            }else {
            	//redirecting to login page
            	response.sendRedirect("unvalid_login.jsp");
            	
            }
            
		}catch (SQLException e) {

            e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	
