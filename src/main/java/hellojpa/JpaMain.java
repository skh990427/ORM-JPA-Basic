package hellojpa;

import jakarta.persistence.*;

import java.util.List;

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
//            //id로 조회
//            Member findMember = em.find(Member.class, 1L)

            List<Member> result = em.createQuery("select m from Member as m", Member.class)
                    .setFirstResult(1) //1번 부터
                    .setMaxResults(10) //10개 가져와 페이징처리
                    .getResultList();

            for (Member member : result) {
                System.out.println("member = " + member.getName());
            }

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
