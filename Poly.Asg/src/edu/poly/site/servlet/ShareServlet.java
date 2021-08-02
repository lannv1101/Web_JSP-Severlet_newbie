package edu.poly.site.servlet;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.common.CookieUtils;
import edu.poly.common.PageInfo;
import edu.poly.common.PageType;
import edu.poly.common.SessionUtils;
import edu.poly.dao.FavoriteDao;
import edu.poly.dao.ShareDao;

/**
 * Servlet implementation class ShareServlet
 */
@WebServlet("/ShareVideo")
public class ShareServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		if (!SessionUtils.isLogin(request)) {
			response.sendRedirect("/Login");
			return;
		}

		String videoId = request.getParameter("videoId");

		if (videoId == null) {
			response.sendRedirect("/Homepage");
			return;
		}
		request.setAttribute("videoId", videoId);

		request.getRequestDispatcher("/sites/videos/share.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		sendmail(request, response);
		saveShare(request, response);
		request.getRequestDispatcher("/sites/videos/share.jsp").forward(request, response);

	}

	private void saveShare(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			// String username = request.getParameter("username");
			String username = SessionUtils.getLoginedUsername(request);

			String videoid = request.getParameter("videoId");
			String email = request.getParameter("receiver");

			ShareDao dao = new ShareDao();
			dao.saveShare(username, videoid, email);
			
			System.out.println("Share thanh cong");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Share that bai");
		}

	}

	private void sendmail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		final String username = "lannvpd03981@fpt.edu.vn";
		final String password = "0378795129a";
		Properties prop = new Properties();

		prop.put("mail.smtp.host", "smtp.gmail.com");
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");
		prop.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}

		});
		String videoid = request.getParameter("videoId");
		String receiveremain = request.getParameter("receiver");
		String subject = request.getParameter("subject");
		String messagesend = request.getParameter("message");
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiveremain));

			message.setSubject(subject);
			StringBuilder sb = new StringBuilder();
			
			sb.append("Dear Ms/Mr...");
			sb.append(" The video is more interesting and i want to share with you");
			sb.append(" Please click the link: http://localhost:8199/Poly.Asg/Detail?videoId="+videoid );
			sb.append(" Thank you!! ");
			sb.append(username);
			message.setText(sb.toString()+messagesend);
		
			Transport.send(message);

			System.out.println("send thanh cong");
			request.setAttribute("message", "Send link video is succesfully");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("send that bai");
			request.setAttribute("message", "Send link video is fail");
		}

	}

}
