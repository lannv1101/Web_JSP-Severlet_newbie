package edu.poly.site.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.FavoriteDao;
import edu.poly.dao.UserDao;
import edu.poly.dao.VideoDao;
import edu.poly.model.Favorite;
import edu.poly.model.User;
import edu.poly.model.Video;

/**
 * Servlet implementation class HomepageServlet
 */
@WebServlet("/Homepage")
public class HomepageServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		findAll(request, response);
		findAllbyUser(request, response);
		// findRole(request, response);

		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_HOME_PAGE);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		findAll(request, response);
		findAllbyUser(request, response);
		// findRole(request, response);
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_HOME_PAGE);
	}

	private void findAll(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		HttpSession session= request.getSession();
		try {
			session.getAttribute("username");
			VideoDao dao = new VideoDao();
			List<Video> list = dao.findAll();
			request.setAttribute("videos", list);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			request.setAttribute("error", "Error: " + e.getMessage());
		}

	}
	private void findAllbyUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = SessionUtils.getLoginedUsername(request);
		try {
			FavoriteDao dao = new FavoriteDao();

			List<Favorite> favList = dao.FindAllFavoriteByUser(username);
			request.setAttribute("favList", favList);

			System.out.println("tai favorite thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("tai favorite that bai");

		}
		
	}

	// homepage chua tich hop hien thi trang thai admin khi kich ve homepage

	/*
	 * private void findRole(HttpServletRequest request, HttpServletResponse
	 * response) { // TODO Auto-generated method stub HttpSession session =
	 * request.getSession(); try {
	 * 
	 * String username = (String) session.getAttribute("username"); UserDao dao =
	 * new UserDao(); User user = dao.findById(username);
	 * request.setAttribute("role", user.isAdmin() == true);
	 * 
	 * } catch (Exception e) { // TODO: handle exception e.printStackTrace();
	 * request.setAttribute("error", "Error: " + e.getMessage()); }
	 * 
	 * }
	 * 
	 */
	
	
}
