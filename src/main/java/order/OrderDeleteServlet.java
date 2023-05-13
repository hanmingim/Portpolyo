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
 * Servlet implementation class OrderDeleteServlet
 */
@WebServlet("/orderDelete.do")
public class OrderDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderDeleteServlet() {
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
		String orderno=req.getParameter("OID");//��ȣ
		String name=req.getParameter("NAME");//�̸�
		String tel=req.getParameter("TEL");//��ȭ
		String price=req.getParameter("PRICE");//�ּ�
		String item=req.getParameter("ITEM");//�ּ�
		String orderdate=req.getParameter("ODATE");//������
		//2022-07-29 
		orderdate=orderdate.substring(0,4)+orderdate.substring(5,7)+
				orderdate.substring(8);
		String gender=req.getParameter("GENDER");//�����ڵ�
		String type=req.getParameter("TYPE");//�����ڵ�
		String tbnum=req.getParameter("TBNUM");//�����ڵ�
		String delete="delete from order_tbl_02 where orderno=?";
		Connection con=null; PreparedStatement pstmt=null;
		String result="";
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@//localhost:1521/XE",
					"hr","hr");
			pstmt=con.prepareStatement(delete);
			pstmt.setString(1, name);
			pstmt.setString(2, tel);
			pstmt.setString(3, price);
			pstmt.setString(4, item);
			pstmt.setString(5, orderdate);
			pstmt.setString(6, gender);
			pstmt.setString(7, type);
			pstmt.setString(8, tbnum);
			pstmt.setInt(9, Integer.parseInt(orderno));
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
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String btn=request.getParameter("btn");
		if(btn.equals("����")) {
			String result = orderDelete(request);
			//�������� ������ ���(deleteResult.jsp)�� �ٲ�.
			response.sendRedirect(
					"deleteResult.jsp?R="+result);
		}else if(btn.equals("��ȸ")) {
			//��ȸ ������ ȣ���Ѵ�.
			response.sendRedirect("orderAll.do");
		}
	}
	
}
