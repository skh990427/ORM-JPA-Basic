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

            //em.find로 찾아서 영속성 컨텐스트에 가져오면 영속상태가 된다.
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAAA"); // - 변경 감지

            em.detach(member); // 특정 엔티티만 준영속 상태로 전환
            em.clear(); // 영속성 컨텍스트 안에 모든 엔티티를 준영속 상태로 전환(초기화)
            em.close(); // 영속성 컨텍스트를 그냥 닫아버림

            tx.commit(); //트랜잭션이 커밋하는 시점에 영속성 컨텍스트에 있는 값이 날아가서 저장된다!
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
