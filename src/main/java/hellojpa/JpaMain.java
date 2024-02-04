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

            Team team = new Team();
            team.setName("TeamA");
//            team.getMembers().add(member); //team쪽에는 mappedby로 연관관계를 설정했기때문에 얘는 읽기 전용이다.
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team); //양방향 연관관계 : 연관관계의 주인
            em.persist(member);

//            team.getMembers().add(member); //순수 객체 상태를 고려하여 양쪽에 값을 셋팅해야함 / 연관관계 메서드 setTeam 메서드를 만들어서 쓰자

//            em.flush();
//            em.clear();

            Team findTeam = em.find(Team.class, team.getId());
            List<Member> members = findTeam.getMembers();

            System.out.println("===");
            for (Member m : members) {
                System.out.println("m = " + m.getUsername());
            }
            System.out.println("===");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
