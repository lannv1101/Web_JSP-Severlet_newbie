package edu.poly.admin.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.dao.FavoriteDao;
import edu.poly.dao.ShareDao;
import edu.poly.dao.VideoDao;
import edu.poly.domain.FavoriteReport;
import edu.poly.domain.FavoriteUserReport;
import edu.poly.domain.ShareUserReport;
import edu.poly.model.Share;
import edu.poly.model.Video;

/**
 * Servlet implementation class ReportsManagementServlet
 */
@WebServlet("/Admin/ReportsManagementServlet")
public class ReportsManagementServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		reportFavoritesByVideos(request, response);
		reportFavoriteUserByVideos(request, response);
		reportShareUserByVideo(request, response);
		PageInfo.prepareAndForward(request, response, PageType.REPORT_MANAGEMENT_PAGE);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	protected void reportFavoritesByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			FavoriteDao dao = new FavoriteDao();

			List<FavoriteReport> list = dao.reportFavoritesByVideos();

			request.setAttribute("favList", list);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Error: " + e.getMessage());
		}

	}

	protected void reportFavoriteUserByVideos(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String videoUserId = request.getParameter("videoUserId");

			VideoDao vdao = new VideoDao();
			List<Video> vList = vdao.findAll();
			if (videoUserId == null && vList.size() > 0) {
				videoUserId = vList.get(0).getVideoId();
			}
			FavoriteDao dao = new FavoriteDao();

			List<FavoriteUserReport> list = dao.reportFavoriteUserByVideo(videoUserId);

			request.setAttribute("videoUserId", videoUserId);
			request.setAttribute("vidList", vList);
			request.setAttribute("favUsers", list);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Error: " + e.getMessage());
		}

	}
	protected void reportShareUserByVideo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String shareUserId = request.getParameter("shareUserId");

			VideoDao sdao = new VideoDao();
			List<Video> sList = sdao.findAll();
			if (shareUserId == null && sList.size() > 0) {
				shareUserId = sList.get(0).getVideoId();
			}
			ShareDao dao = new ShareDao();

			List<ShareUserReport> list = dao.reportSharesByVideo(shareUserId);

			request.setAttribute("shareUserId", shareUserId);
			request.setAttribute("shaList", sList);
			request.setAttribute("shaUsers", list);
		} catch (Exception e) {
			// TODO: handle exception
			request.setAttribute("error", "Error: " + e.getMessage());
		}

	}

}
