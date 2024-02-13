package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        /**
         * 모든 데이터의 변경은 트랜잭션 안에서 변경되어야함
         */
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("member1");
            member1.setTeam(team);

            em.persist(member1);

            em.flush();
            em.clear();

            Member m = em.find(Member.class, member1.getId());

            System.out.println("m.getTeam().getClass() = " + m.getTeam().getClass());

            System.out.println("==========");
            m.getTeam().getName(); //이때 쿼리가 나감
            System.out.println("==========");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }

        emf.close();
    }
}