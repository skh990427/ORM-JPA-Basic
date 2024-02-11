package hellojpa;

import jakarta.persistence.*;

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

            Member member1 = new Member();
            member1.setUsername("member1");
            em.persist(member1);

            em.flush();
            em.clear();

            Member m1 = em.getReference(Member.class, member1.getId()); //영속성 컨텍스트에 올라가있는 상태
            System.out.println("m1.getClass() = " + m1.getClass());

            Member reference = em.getReference(Member.class, member1.getId());
            System.out.println("reference.getClass() = " + reference.getClass());

            System.out.println("m1 == reference = " + (m1 == reference));

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}