package order;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		String id = request.getParameter("ID");
		String pwd = request.getParameter("PWD");
		//고객정보 테이블에 있는 계정봐 암호와 비교한다.
		String select = "select password from "
				+ "member_info_tbl where id = ?";
		String result = "";//로그인 결과를 위한 변수 선언
		Connection con=null; PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@//localhost:1521/XE",
							"hr","hr");
			pstmt=con.prepareStatement(select);
			pstmt.setString(1,  id);//첫번째 물음표에 계정 설정
			rs = pstmt.executeQuery();//select 실행
			if(rs.next()) {//조회결과로 이동 성송
				String pwdInDB = rs.getString(1);//조회결과
				if(pwdInDB.equals(pwd)) {
					//입력한 암호와 DB의 암호를 비교
					result = "OK";//로그인 성공
					HttpSession session = 
							request.getSession();
					session.setAttribute("LOGINID", id);
				}else {
					result = "NOPWD";//암호 불일치
				}
			}else {//조회결과로 이동 실패 즉 조회결과가 없다.
				result = "NOID";//계정 없음
			}
		}catch(Exception e) {
		}finally {
			try {
				con.close(); pstmt.close(); rs.close();
			}catch(Exception e) {}
			
		}
		//로그인 결과 JSP(loginResult.jsp)로 전환
		response.sendRedirect("loginResult.jsp?R="+result);
	}

}
