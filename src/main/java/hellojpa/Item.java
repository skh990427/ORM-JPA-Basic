package hellojpa;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED) //전략 선택, 기본값은 싱글 테이블
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;
}
