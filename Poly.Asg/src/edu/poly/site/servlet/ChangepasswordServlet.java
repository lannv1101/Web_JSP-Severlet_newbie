package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDao;
import edu.poly.model.User;

/**
 * Servlet implementation class ChangepasswordServlet
 */
@WebServlet("/Changepassword")
public class ChangepasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = SessionUtils.getLoginedUsername(request);

		if (username == null) {
			request.getRequestDispatcher("/Login").forward(request, response);
			return;
		}

		try {
			UserDao dao = new UserDao();

			User user = dao.findById(username);
			request.setAttribute("user", user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		request.getRequestDispatcher("sites/users/change-password.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = new User();
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");

		try {

			String username = SessionUtils.getLoginedUsername(request);
			UserDao dao = new UserDao();
			User oldUser = dao.findById(username);
			if (oldUser.getPassword().equals(password)) {
				user.setUsername(oldUser.getUsername());
				user.setAdmin(oldUser.isAdmin());
				user.setPassword(password2);
				user.setFullname(oldUser.getFullname());
				user.setEmail(oldUser.getEmail());
				dao.update(user);
				
				request.setAttribute("message", "Change Password Successfully!");
				request.setAttribute("user", user);
			}else {
			request.setAttribute("message", "Old password does not match the current password");
			
			request.getRequestDispatcher("sites/users/change-password.jsp").forward(request, response);
			request.setAttribute("user", user);
			return;
			}
			

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		request.getRequestDispatcher("sites/users/change-password.jsp").forward(request, response);
		
	}
}
