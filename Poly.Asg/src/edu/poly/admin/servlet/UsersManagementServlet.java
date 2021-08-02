package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.UserDao;
import edu.poly.dao.VideoDao;
import edu.poly.model.User;
import edu.poly.model.Video;

/**
 * Servlet implementation class UsersManagementServlet
 */
@MultipartConfig
@WebServlet({ "/Admin/UsersManagement", "/Admin/UsersManagement/create", "/Admin/UsersManagement/update",
		"/Admin/UsersManagement/delete", "/Admin/UsersManagement/reset", "/Admin/UsersManagement/edit"

})
public class UsersManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String url = request.getRequestURL().toString();
		if (url.contains("edit")) {
			edit(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("reset")) {
			reset(request, response);
			return;
		}

		findAll(request, response);
		User user  = new User();
		request.setAttribute("user", user);
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();

		if (url.contains("create")) {
			create(request, response);
			return;
		}
		if (url.contains("delete")) {
			delete(request, response);
			return;
		}
		if (url.contains("update")) {
			update(request, response);
			return;
		}
		if (url.contains("reset")) {
			reset(request, response);
			return;
		}
		findAll(request, response);

	}

	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			User user = new User();
			BeanUtils.populate(user, request.getParameterMap());

			String username = request.getParameter("username");
			UserDao dao = new UserDao();
			User oldUser = dao.findById(username);

			user.setAdmin(oldUser.isAdmin());
			dao.update(user);
			request.setAttribute("message", "User is updated!");
			request.setAttribute("user", user);
			
			findAll(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();

		try {

			BeanUtils.populate(user, request.getParameterMap());

			UserDao dao = new UserDao();
			user.setAdmin(true);
			dao.insert(user);
			request.setAttribute("user", user);
			request.setAttribute("message", "User is inserted!");
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e.getMessage());
		}

		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

	}

	private void reset(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = new User();
		request.setAttribute("user", user);
		findAll(request, response);
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("username");

		if (id == null) {
			request.setAttribute("error", "username is required!");
			PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

			return;
		}

		try {
			UserDao dao = new UserDao();
			User user = dao.findById(id);

			if (user == null) {
				request.setAttribute("error", "username is not found!");
				PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

				return;
			}

			dao.delete(id);
			request.setAttribute("message", "User is deleted!");
			request.setAttribute("user", new User());
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			// TODO: handle exception
		}
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

	}

	private void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("username");

		if (id == null) {
			request.setAttribute("error", "Username is required!");
			PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

			return;
		}

		try {
			UserDao dao = new UserDao();
			User user = dao.findById(id);
			request.setAttribute("user", user);
			findAll(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
			// TODO: handle exception
		}
		PageInfo.prepareAndForward(request, response, PageType.USER_MANAGEMENT_PAGE);

	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			UserDao dao = new UserDao();

			List<User> list = dao.findAll();
			request.setAttribute("users", list);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

	}

}
