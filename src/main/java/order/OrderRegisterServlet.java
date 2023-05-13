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
		request.setCharacterEncoding("UTF-8");//�ѱ�ó��
		String orderno=request.getParameter("OID");//ȸ����ȣ
		String name=request.getParameter("NAME");//�̸�
		String tel=request.getParameter("TEL");//��ȭ��ȣ
		String price=request.getParameter("PRICE");//�ּ�
		String item=request.getParameter("ITEM");//�ּ�
		String orderdate=request.getParameter("ODATE");//������
		//2022-07-28 ---> 20220728
		String year = orderdate.substring(0,4);
		String month = orderdate.substring(5,7);
		String date = orderdate.substring(8);
		orderdate = year+month+date;
		String gender=request.getParameter("GENDER");//�����
		String type=request.getParameter("TYPE");//�����
		String tbnum=request.getParameter("TBNUM");//�����ڵ�
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
			pstmt.setInt(1, Integer.parseInt(orderno));//ȸ����ȣ
			pstmt.setString(2, name);//�̸�
			pstmt.setString(3, tel);//��ȭ��ȣ
			pstmt.setString(4, price);//�ּ�
			pstmt.setString(5, item);//������
			pstmt.setString(6, orderdate);//���
			pstmt.setString(7, gender);//�����ڵ�
			pstmt.setString(8, type);//�����ڵ�
			pstmt.setString(9, tbnum);//�����ڵ�
			pstmt.executeUpdate();//insert ����
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
