package edu.poly.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.poly.domain.FavoriteUserReport;
import edu.poly.domain.ShareUserReport;
import edu.poly.model.Favorite;
import edu.poly.model.Share;
import edu.poly.model.User;
import edu.poly.model.Video;

public class ShareDao extends AbstractEntityDao<Share> {

	public ShareDao() {
		super(Share.class);
		// TODO Auto-generated constructor stub
	}
	public List<ShareUserReport> reportSharesByVideo(String videoId) {

		String jpql = "select new edu.poly.domain.ShareUserReport(s.user.username, s.user.email, "
				+ "s.emails, s.shareDate ) from Share s where s.video.videoId= :videoId";

		EntityManager em = JpaUtils.getEntityManager();

		List<ShareUserReport> list = null;

		try {
			TypedQuery<ShareUserReport> query = em.createQuery(jpql, ShareUserReport.class);

			query.setParameter("videoId", videoId);
			list = query.getResultList();

		} finally {
			em.close();
		}
		return list;

	}

	public void saveShare(String username, String videoid, String email) {
		EntityManager em= JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		Share sh = new Share();
		User u = em.find(User.class, username);
		Video v = em.find(Video.class, videoid);
		sh.setUser(u);
		sh.setVideo(v);
		sh.setEmails(email);
		sh.setShareDate(new Date());

		try {
			trans.begin();
			em.persist(sh);
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trans.rollback();

		} finally {
			em.close();
		}
	}
}
