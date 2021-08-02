package edu.poly.site.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.dao.VideoDao;
import edu.poly.model.Video;

/**
 * Servlet implementation class Detail
 */
@WebServlet("/DetailVideo")
public class Detail extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	public void findbyid(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			
		Video video = new Video();
		
		VideoDao vdao = new VideoDao();
		
		String videoid = request.getParameter("videoId");
		video = vdao.findById(videoid);
		
		request.setAttribute("video", video);
	}

}
