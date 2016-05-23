package jpa_sample.test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import jpa_sample.test.T1;

public class Main {

	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
		EntityManager em = emf.createEntityManager();
		
		EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("myPersistenceUnit");
		EntityManager em2 = emf2.createEntityManager();

		EntityManagerFactory emf3 = Persistence.createEntityManagerFactory("myPersistenceUnit");
		EntityManager em3 = emf3.createEntityManager();
		
		em3.getTransaction().begin();
		em2.getTransaction().begin();
		em.getTransaction().begin();

		
		T1 taro = new T1();
		taro.setName("taro");
		em.persist(taro);
		em.getTransaction().commit();
		
		
		T1 taro2 = em2.find(T1.class, (long)10);
		taro2.setName("pom");
		em2.persist(taro2);

		T1 taro3 = em3.find(T1.class,(long)10);
		taro3.setName("beny");
		em3.merge(taro3);
//		em3.getTransaction().setRollbackOnly();

		em3.getTransaction().commit();
		em2.getTransaction().commit();
		
		String sql = "from T1 where name = ?";
		T1 t1 = (T1) em.createQuery(sql).setParameter(1, "pom").getSingleResult();
		System.out.println("### RESULT: " + t1);
	}

}


