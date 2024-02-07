package hellojpa;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //전략 선택, 기본값은 싱글 테이블
@DiscriminatorColumn //DTYPE 컬럽 추가 이름은 바꿀 수 있는데 그냥 관례로 쓰는편이 좋음, 단인테이블 전략은 이 애노테이션 없어도 됨 DTYPE 없으면 싱글테이블전략은 이게 뭔지 알수가없어서 자동으로 추가됨
public class Item {

    @Id @GeneratedValue
    private Long id;

    private String name;
    private int price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
