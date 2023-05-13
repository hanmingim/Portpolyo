package order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderRegisterServlet
 */
@WebServlet("/orderRegister.do")
public class OrderRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderRegisterServlet() {
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
		request.setCharacterEncoding("UTF-8");//한글처리
		String orderno=request.getParameter("OID");//회원번호
		String name=request.getParameter("NAME");//이름
		String tel=request.getParameter("TEL");//전화번호
		String price=request.getParameter("PRICE");//주소
		String item=request.getParameter("ITEM");//주소
		String orderdate=request.getParameter("ODATE");//가입일
		//2022-07-28 ---> 20220728
		String year = orderdate.substring(0,4);
		String month = orderdate.substring(5,7);
		String date = orderdate.substring(8);
		orderdate = year+month+date;
		String gender=request.getParameter("GENDER");//고객등급
		String type=request.getParameter("TYPE");//고객등급
		String tbnum=request.getParameter("TBNUM");//도시코드
		String insert="insert into order_tbl_02 "+
				"values(?,?,?,?,?,to_date(?,'YYYYMMDD'),?,?,?)";
		Connection con=null; PreparedStatement pstmt=null;
		String result="";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@//localhost:1521/XE",
							"hr","hr");
			pstmt=con.prepareStatement(insert);
			pstmt.setInt(1, Integer.parseInt(orderno));//회원번호
			pstmt.setString(2, name);//이름
			pstmt.setString(3, tel);//전화번호
			pstmt.setString(4, price);//주소
			pstmt.setString(5, item);//가입일
			pstmt.setString(6, orderdate);//등급
			pstmt.setString(7, gender);//도시코드
			pstmt.setString(8, type);//도시코드
			pstmt.setString(9, tbnum);//도시코드
			pstmt.executeUpdate();//insert 실행
			result="OK";
		}catch(Exception e) {
			result="NOK";
		}finally {
			try {
				con.close(); pstmt.close();
			}catch(Exception e) {}
		}
		response.sendRedirect("registerResult.jsp?R="+result);
	}

}
