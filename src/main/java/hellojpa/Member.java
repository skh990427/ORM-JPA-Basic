package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Member {

    @Id //pk 매핑
//    @GeneratedValue(strategy = GenerationType.AUTO) //기본값 DB 방언에 맞춰 자동으로 생성
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스에 아이디 생성 위임
    @GeneratedValue(strategy = GenerationType.SEQUENCE) //오라클 같은곳에서 많이 사용하는 전략
//    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @Column(name = "name")
    private String username;

    protected Member() {
    }

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
}
