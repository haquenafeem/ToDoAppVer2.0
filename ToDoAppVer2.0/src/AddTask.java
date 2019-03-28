

import java.io.IOException;
//import java.io.PrintWriter;
//import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddTask
 */
@WebServlet("/AddTask")
public class AddTask extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTask() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		/*PrintWriter out=response.getWriter();
		out.println("<html><body>");
		out.println("Logged Out Successfully !");
		out.println("Continue to Log-In page");
		out.println("<a href=\"app.jsp\">Log-In</a>");
		out.println("</body></html>");*/
		
		request.getSession().invalidate();
		request.getSession().setAttribute("username", LoginCheck.name);
		request.getRequestDispatcher("app.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		//Getting the global username from LoginCheck
		String username=LoginCheck.name;
		//getting the task that will be added
		String task=request.getParameter("taskadd");
		String report = "false";
		/*PrintWriter out=response.getWriter();
		out.println("<html><body>");
		out.println("<h2>helloworld</h2>");
		out.println("<p>"+username+" "+task+"</p>");
		out.println("</body></html>");*/
		try {
			//connecting to database
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp","root","");

            Statement st = conn.createStatement();
            //inserting task to database
            String sql = "insert into tasklist (username,task,report) values('"+username+"','"+task+"','"+report+"')";

            st.executeUpdate(sql);
            //redirecting to app.jsp
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("app.jsp");
            requestDispatcher.forward(request, response);
               }catch (ClassNotFoundException e) {

              e.printStackTrace();

        } catch (SQLException e) {

              e.printStackTrace();

         }
		
	}

}
