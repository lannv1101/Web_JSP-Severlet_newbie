package edu.poly.site.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.FavoriteDao;
import edu.poly.model.Favorite;
import edu.poly.model.User;
import edu.poly.dao.JpaUtils;

/**
 * Servlet implementation class FavoriteServlet
 */
@MultipartConfig
@WebServlet({ "/Favorite", "/Favorite/Like", "/Favorite/Unlike" })
public class FavoriteServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = request.getRequestURL().toString();
		if (url.contains("Like")) {
			create(request, response);
			return;
		}
		if (url.contains("Unlike")) {
			delete(request, response);
			findAllbyUser(request, response);
			return;
		}
		if (url.contains("Favorite")) {
			findAllbyUser(request, response);

			return;
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	
	}

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			String username = request.getParameter("username");
			// String username = CookieUtils.get("username", request);

			String videoid = request.getParameter("videoId");

			FavoriteDao dao = new FavoriteDao();
			dao.saveLike(username, videoid);
			request.setAttribute("message", "Video is inserted your favorite");
			System.out.println("Them thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Them that bai");
			request.setAttribute("error", "Video  inserted is fail");
		}
		request.getRequestDispatcher("/Homepage").forward(request, response);

	}

	/*
	 * private void createLikeFavorite(HttpServletRequest request,
	 * HttpServletResponse response) throws ServletException, IOException { Favorite
	 * fav = new Favorite();
	 * 
	 * 
	 * try { String user = request.getParameter("username");
	 * 
	 * BeanUtils.populate(fav, request.getParameterMap());
	 * 
	 * 
	 * FavoriteDao favDao= new FavoriteDao(); fav.setUser(user); fav.setLikeDate(new
	 * Date()); favDao.insert(fav);
	 * 
	 * request.setAttribute("message", "FavoriteLike is inserted!");
	 * 
	 * } catch (Exception e) { e.printStackTrace(); request.setAttribute("error",
	 * e.getMessage()); }
	 * 
	 * PageInfo.prepareAndForwardSite(request, response, PageType.SITE_HOME_PAGE);
	 * 
	 * }
	 */

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
		PageInfo.prepareAndForwardSite(request, response, PageType.SITE_FAVORITE_PAGE);

	}

	private void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		try {
			
			String username = SessionUtils.getLoginedUsername(request);
			String videoid = request.getParameter("videoId");
			FavoriteDao dao = new FavoriteDao();
		
			dao.deleteLike(username, videoid);
			
		
			request.setAttribute("message", "Video is deleted your favorite");
			System.out.println("Xoa favorite thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Xoa favorite that bai");
			request.setAttribute("error", "Unlike favorite video fail");
		}

	}

}
