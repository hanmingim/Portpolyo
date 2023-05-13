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
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrderEntryServlet
 */
@WebServlet("/orderEntry.do")
public class OrderEntryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderEntryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//�α��� ���θ� �˻��Ѵ�.
		HttpSession session = request.getSession();
		String id=(String)session.getAttribute("LOGINID");
		if(id == null) {
			response.sendRedirect("login.jsp?M=Y");
		}else {
		
		//���������̺��� ���� ū ȸ����ȣ�� ã�´�.
		String select="select max(orderno) from order_tbl_02";
		int orderno = 0;//��ȸ�� ����ȣ�� ���� ����
		Connection con=null; Statement stmt=null;
		ResultSet rs = null;
		try {
				Class.forName("oracle.jdbc.OracleDriver");
				con = DriverManager.getConnection
						("jdbc:oracle:thin:@//localhost:1521/XE",
								"hr","hr");
				stmt=con.createStatement();
				rs = stmt.executeQuery(select);//select ����
				rs.next();//��ȸ ����� �̵�
				orderno = rs.getInt(1);//ù��° ��ȸ����� ����
				orderno = orderno + 1;//�� ���� ����ȣ
		}catch(Exception e) {
			
		}finally {
			try {
				con.close(); stmt.close(); rs.close();
			}catch(Exception e) {}
			
		}
		//custno�� entry.jsp�� �Ѱ��ش�.
		//Forward
		RequestDispatcher rd = 
				request.getRequestDispatcher(
						"entry.jsp?OID="+orderno);
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
