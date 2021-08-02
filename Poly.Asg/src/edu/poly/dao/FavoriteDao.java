package edu.poly.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import edu.poly.domain.FavoriteReport;
import edu.poly.domain.FavoriteUserReport;
import edu.poly.model.Favorite;
import edu.poly.model.User;
import edu.poly.model.Video;

public class FavoriteDao extends AbstractEntityDao<Favorite> {

	public FavoriteDao() {
		super(Favorite.class);
	}

	public List<FavoriteUserReport> reportFavoriteUserByVideo(String videoId) {

		String jpql = "select new edu.poly.domain.FavoriteUserReport(f.user.username, f.user.fullname, "
				+ "f.user.email, f.likeDate ) from Favorite f where f.video.videoId= :videoId";

		EntityManager em = JpaUtils.getEntityManager();

		List<FavoriteUserReport> list = null;

		try {
			TypedQuery<FavoriteUserReport> query = em.createQuery(jpql, FavoriteUserReport.class);

			query.setParameter("videoId", videoId);
			list = query.getResultList();

		} finally {
			em.close();
		}
		return list;

	}

	public List<FavoriteReport> reportFavoritesByVideos() {

		String jpql = "select new edu.poly.domain.FavoriteReport(f.video.title, count(f), min(f.likeDate) , max(f.likeDate)) "
				+ "from Favorite f group by f.video.title";

		EntityManager em = JpaUtils.getEntityManager();

		List<FavoriteReport> list = null;
		try {
			TypedQuery<FavoriteReport> query = em.createQuery(jpql, FavoriteReport.class);
			list = query.getResultList();

		} finally {
			em.close();
		}
		return list;

	}

	public void saveLike(String username, String videoid) {
		EntityManager em= JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		Favorite fa = new Favorite();
		User u = em.find(User.class, username);
		Video v = em.find(Video.class, videoid);
		fa.setUser(u);
		fa.setVideo(v);
		fa.setLikeDate(new Date());

		try {
			trans.begin();
			em.persist(fa);
			trans.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			trans.rollback();

		} finally {
			em.close();
		}
	}
	public void deleteLike(String username, String videoid) {
		EntityManager em= JpaUtils.getEntityManager();
		EntityTransaction trans = em.getTransaction();
		
		
		
		TypedQuery<Favorite> q = em.createQuery("SELECT f FROM Favorite f WHERE f.user.username=:username AND f.video.videoId=:videoid", Favorite.class);
		q.setParameter("username", username);
		q.setParameter("videoid", videoid);
		
		if (q.getResultList().size()>0) {
			System.out.println("Here---"+q.getResultList().get(0).getVideo());
			try {
				trans.begin();
				em.remove(em.merge(q.getResultList().get(0)));
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
	
	
	public List <Favorite> FindAllFavoriteByUser(String username){
		String jpql = "select f from Favorite f where f.user.username = :username";

		EntityManager em = JpaUtils.getEntityManager();

		List<Favorite> list = null;
		try {
			TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
			
			query.setParameter("username", username);
			
			list  = query.getResultList();
		} finally {
			// TODO: handle exception
			em.close();
		}
		
		return list;
		
	}
	
	
	
}
