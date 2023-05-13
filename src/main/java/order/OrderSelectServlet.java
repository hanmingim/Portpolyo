package order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class OrderSelectServlet
 */
@WebServlet("/orderSelect.do")
public class OrderSelectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderSelectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String orderno=request.getParameter("OID");
		String select="select orderno,name, "+
				"tel,price,item,to_char(orderdate,'YYYY-MM-DD'),"+
				"gender,type,table_number from order_tbl_02 "+
				"where orderno = "+orderno;
		Connection con=null; Statement stmt=null;
		ResultSet rs=null;	Order odr = new Order();
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@//localhost:1521/XE",
					"hr","hr");
			stmt=con.createStatement();
			rs=stmt.executeQuery(select);
			rs.next();//��ȸ ����� �̵�
			odr.setOrderno(rs.getInt(1));//����ȣ
			odr.setName(rs.getString(2));//�̸�
			odr.setTel(rs.getString(3));//��ȭ��ȣ
			odr.setPrice(rs.getString(4));//�ּ�
			odr.setItem(rs.getString(5));//������
			odr.setOrderdate(rs.getString(6));//���
			odr.setGender(rs.getString(7));//�����ڵ�
			odr.setType(rs.getString(8));//�����ڵ�
			odr.setTbnum(rs.getString(9));//�����ڵ�
		}catch(Exception e) {
			
		}finally {
			try {
				con.close(); stmt.close(); rs.close();
			}catch(Exception e) {}
		}
		request.setAttribute("ODR",odr);
		RequestDispatcher r = 
				request.getRequestDispatcher("orderDetail.jsp");
		r.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
