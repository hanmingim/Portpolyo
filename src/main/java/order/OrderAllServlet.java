package order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrderAllServlet
 */
@WebServlet("/orderAll.do")
public class OrderAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("LOGINID");
		if(id == null) {
			response.sendRedirect("login.jsp?M=Y");
		}else {
			
		String select="select orderno,name, "+
				"tel,price,item,to_char(orderdate,'YYYY-MM-DD'),"+
				"gender,type,table_number from order_tbl_02";
		Connection con=null; Statement stmt=null;
		ResultSet rs=null;
		ArrayList orderAll = new ArrayList();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@//localhost:1521/XE",
							"hr","hr");
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			while(rs.next()) {
				Order odr = new Order();
				odr.setOrderno(rs.getInt(1));//고객번호
				odr.setName(rs.getString(2));//이름
				odr.setTel(rs.getString(3));//전화번호
				odr.setPrice(rs.getString(4));//주소
				odr.setItem(rs.getString(5));//가입일
				odr.setOrderdate(rs.getString(6));//등급
				odr.setGender(rs.getString(7));//도시코드
				odr.setType(rs.getString(8));//도시코드
				odr.setTbnum(rs.getString(9));//도시코드
				orderAll.add(odr);
			}
		}catch(Exception e) {
			
		}finally {
			try {
				con.close(); stmt.close(); rs.close();
			}catch(Exception e) {}
		}
		//조회결과를 출력하는 JSP(orderAll.jsp)로 전환
		//Forward로 한다.
		request.setAttribute("ALL", orderAll);
		RequestDispatcher rd = 
				request.getRequestDispatcher("orderAll.jsp");
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
