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
 * Servlet implementation class OrderUpdateServlet
 */
@WebServlet("/orderUpdate.do")
public class OrderUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderUpdateServlet() {
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
	
	String orderDelete(HttpServletRequest req) {
		Connection con=null; PreparedStatement pstmt=null;
		String orderno=req.getParameter("OID");//번호
		String delete="delete from order_tbl_02 where orderno=?";
		String result="";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@//localhost:1521/XE",
					"hr","hr");
			pstmt=con.prepareStatement(delete);
			pstmt.setInt(1, Integer.parseInt(orderno));
			pstmt.executeUpdate();
			result="OK";
		}catch(Exception e) {
			result="NOK";
			e.printStackTrace();
		}finally {
			try {
				con.close(); pstmt.close();
			}catch(Exception e) {}
		}
		return result;
	}
	
	String orderUpdate(HttpServletRequest req) {
		String orderno=req.getParameter("OID");//번호
		String name=req.getParameter("NAME");//이름
		String tel=req.getParameter("TEL");//전화
		String price=req.getParameter("PRICE");//주소
		String item=req.getParameter("ITEM");//주소
		String orderdate=req.getParameter("ODATE");//가입일
		//2022-07-29 
		orderdate=orderdate.substring(0,4)+orderdate.substring(5,7)+
				orderdate.substring(8);
		String gender=req.getParameter("GENDER");//도시코드
		String type=req.getParameter("TYPE");//도시코드
		String tbnum=req.getParameter("TBNUM");//도시코드
		String update="update order_tbl_02 set name=?,"+
				"tel=?,price=?,item=?,orderdate=to_date(?,'YYYYMMDD'),"+
				"gender=?,type=?,table_number=? where orderno=?";
		Connection con=null; PreparedStatement pstmt=null;
		String result="";//update결과를 위한 변수 선언
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@//localhost:1521/XE",
					"hr","hr");
			pstmt=con.prepareStatement(update);
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			pstmt.setString(3, price);
			pstmt.setString(4, item);
			pstmt.setString(5, orderdate);
			pstmt.setString(6, gender);
			pstmt.setString(7, type);
			pstmt.setString(8, tbnum);
			pstmt.setInt(9, Integer.parseInt(orderno));
			pstmt.executeUpdate();//update실행
			result="OK";
		}catch(Exception e) {
			result="NOK";
			e.printStackTrace();
		}finally {
			try {
				con.close(); pstmt.close();
			}catch(Exception e) {}
		}
		return result;
}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String btn=request.getParameter("btn");
		if(btn.equals("修正")) {
			String result = orderUpdate(request);
			//페이지를 수정의 결과(updateResult.jsp)로 바꿈.
			response.sendRedirect(
					"updateResult.jsp?R="+result);
		}else if(btn.equals("削除")) {
			String result = orderDelete(request);
			response.sendRedirect(
					"deleteResult.jsp?R="+result);
		}else if(btn.equals("照会")) {
			//조회 서블릿을 호출한다.
			response.sendRedirect("orderAll.do");
		}

	}
}
