package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDao;
import edu.poly.model.User;

/**
 * Servlet implementation class ForgotpasswordServlet
 */
@WebServlet("/Forgotpassword")
public class ForgotpasswordServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("sites/users/forgot-password.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String username = request.getParameter("username");

		try {
			User user = new User();
			UserDao dao = new UserDao();
			User oldUser = dao.findById(username);
			if (oldUser.getUsername().equals(username) ) {
				user.setUsername(oldUser.getUsername());
				user.setFullname(oldUser.getFullname());
				user.setEmail(oldUser.getEmail());
				user.setAdmin(oldUser.isAdmin());
				user.setPassword(password2);
				
				dao.update(user);
				request.setAttribute("message", "Forgot password is Successfully");
			
			}
			
			
			
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("sites/users/forgot-password.jsp").forward(request, response);
	}

}
