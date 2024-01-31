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

            Member member = new Member(200L, "member200");
            em.persist(member);

            //flush란? 현재 영속성 컨텍스트와 DB상태를 매칭시켜줌
            em.flush(); //플러시 강제호출 - 거의 쓸일 없음

            tx.commit(); //트랜잭션이 커밋하는 시점에 영속성 컨텍스트에 있는 값이 날아가서 저장된다!
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
