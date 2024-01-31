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

            //비영속 상태
            Member member = new Member();
            member.setId(100L);
            member.setName("HelloJPA");

            //영속 상태
            em.persist(member); //이때 DB에 저장되는것이 아님! 그냥 객체를 영속상태로 변경하는 것 뿐!

            //준영속 상태
            em.detach(member); //준영속 상태로변경! - 준영속이란? = 영속성 컨텍스트에서 다시 지움!

            //실제 db 삭제를 요청하는 상태
            em.remove(member);

            tx.commit(); //트랜잭션이 커밋하는 시점에 영속성 컨텍스트에 있는 값이 날아가서 저장된다!
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
