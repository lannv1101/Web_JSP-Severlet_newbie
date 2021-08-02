package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.UserDao;
import edu.poly.model.User;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/Registration")
public class RegistrationServlet extends HttpServlet {
	
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//PageInfo.prepareAndForwardSite(request, response, PageType.SITE_REGISTRATION_PAGE);
		request.getRequestDispatcher("sites/users/registration2.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		
		try {
			
			BeanUtils.populate(user, request.getParameterMap());
			
			UserDao dao = new UserDao();
			dao.insert(user);
			request.setAttribute("message", "Sign Up Succes");
			//request.getRequestDispatcher("/Login").forward(request, response);
			
				// bi 1 loi la them null van hien message ko hien error
			//return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
			}
		request.setAttribute("user", user);
		request.getRequestDispatcher("sites/users/registration2.jsp").forward(request, response);
		//PageInfo.prepareAndForwardSite(request, response, PageType.SITE_REGISTRATION_PAGE);
		
	}
	

}
