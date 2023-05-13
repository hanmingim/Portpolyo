package sales;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SalesAllServlet
 */
@WebServlet("/salesAll.do")
public class SalesAllServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalesAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id=(String)session.getAttribute("LOGINID");
        if(id == null) {//로그인 안함
            response.sendRedirect("login.jsp?M=Y");
        } else {
            Connection con = null;
            PreparedStatement pstmt = null;
            ResultSet rs = null;
            ArrayList<Output> list = new ArrayList<>();
            try {
                Class.forName("oracle.jdbc.OracleDriver");
                con = DriverManager.getConnection(
                    "jdbc:oracle:thin:@//localhost:1521/XE",
                    "hr","hr"
                );
                String select = "SELECT item, COUNT(item), SUM(price) FROM order_tbl_02 GROUP BY item";
                pstmt = con.prepareStatement(select);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    Output output = new Output();
                    output.setItem(rs.getString(1));
                    output.setCounter(rs.getInt(2));
                    output.setTotal(rs.getInt(3));
                    list.add(output);
                }
            } catch(Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) rs.close();
                    if (pstmt != null) pstmt.close();
                    if (con != null) con.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // Forward로 outputResult.jsp로 전환
            request.setAttribute("LIST", list);
            RequestDispatcher rd = request.getRequestDispatcher("outputResult.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        doGet(request, response);
    }

}
