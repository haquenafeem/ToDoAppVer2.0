

import java.io.IOException;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationProcess
 */
@WebServlet("/RegistrationProcess")
public class RegistrationProcess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrationProcess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		response.setContentType("text/html");
		//PrintWriter out=response.getWriter();
		/*out.println("<html><body>");
		out.println("<h2>helloworld</h2>");
		out.println("<p>"+request.getParameter("username")+","+request.getParameter("email")+","+request.getParameter("password")+"</p>");
		out.println("</body></html>");*/
		
		//Getting information for the user registration
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		  try {
			  	//connecting to database
	            Class.forName("com.mysql.jdbc.Driver");

	            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp","root","");

	            Statement st = conn.createStatement();
	            //inserting user information
	            String sql = "insert into userinfo (username,email,password) values('"+username+"','"+email+"','"+password+"')";

	            st.executeUpdate(sql);
	            //redirecting to login page
	            response.sendRedirect("registration_done.jsp");
	               }catch (ClassNotFoundException e) {

	              e.printStackTrace();

	        } catch (SQLException e) {

	              e.printStackTrace();

	         }
	}

}
