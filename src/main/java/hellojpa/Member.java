package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
//@SequenceGenerator(name = "member_seq_generator", sequenceName = "member_seq")
@TableGenerator(name = "MEMBER_SEQ_GENERATOR",
table = "MY_SEQUENCES",
pkColumnValue = "MEMBER_SEQ", allocationSize = 1)
public class Member {

    @Id //pk 매핑
//    @GeneratedValue(strategy = GenerationType.AUTO) //기본값 DB 방언에 맞춰 자동으로 생성
//    @GeneratedValue(strategy = GenerationType.IDENTITY) //데이터베이스에 아이디 생성 위임
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "member_seq_generator") //오라클 같은곳에서 많이 사용하는 전략
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "MEMBER_SEQ_GENERATOR") //키 생성 전용 테이블을 하나 만들어서 시퀀스를 흉내내는 전략, 장점: 모든 DB에 적용가능, 단점: 성능..
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
