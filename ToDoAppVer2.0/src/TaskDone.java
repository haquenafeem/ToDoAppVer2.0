

import java.io.IOException;
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
 * Servlet implementation class TaskDone
 */
@WebServlet("/TaskDone")
public class TaskDone extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TaskDone() {
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
		System.out.println("Im here");
		//Getting id 
		String id=request.getParameter("check").toString();
		
		System.out.println(id);
		try {
			// connecting to database
            Class.forName("com.mysql.jdbc.Driver");

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todoapp","root","");

            Statement st = conn.createStatement();
            // checking the task completed
            String sql = "UPDATE tasklist SET report = 'true' WHERE id="+Integer.parseInt(id);

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
