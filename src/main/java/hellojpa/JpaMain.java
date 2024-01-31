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
            Member member = em.find(Member.class, 150L);

            member.setName("ZZZZZ");

            //JPA는 컬렉션처럼 운영 된다!
            //em.persist(member); - persist 호출해야되는것 아니야?! 는 무슨 하핫! 변경 감지!

            tx.commit(); //트랜잭션이 커밋하는 시점에 영속성 컨텍스트에 있는 값이 날아가서 저장된다!
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
