package hellojpa;

import jakarta.persistence.*;

@Entity
public class Member {

    @Id //pk 매핑
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;

//    @Column(name = "TEAM_ID")
//    private Long teamId;

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    public void changeTeam(Team team) { //연관관계 편의 메서드는 양쪽 중 한곳에서만 만들어야한다.
        this.team = team;

        team.getMembers().add(this); //연관관계에 값을 집어넣을때 다른쪽 연관관계의 값도 업데이트 해서 쓰자
    }
}
