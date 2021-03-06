package controler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bussinesService.LoginMetode;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "ov je LoginServlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		
		LoginMetode metode = new LoginMetode ();
		
		if (metode.daLiPostojiUser(userName)) {
			
			if (metode.daLiJeDobarPassword(userName, password)) {
				if (metode.vratiUsera(userName, password) != null) {
					
					
					
				}else {
					response.sendRedirect("error.html");
				}
			}else {
				response.sendRedirect("loginError.html");
			}
			
		}else {
			response.sendRedirect("loginError.html");
		}
	}

}
