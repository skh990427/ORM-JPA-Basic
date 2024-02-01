package hellojpa;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Member {

    @Id //pk 매핑
    private Long id;

    @Column(name = "name", insertable = true, updatable = false, nullable = false, unique = true, length = 100)
    private String username;

    private Integer age;

    @Enumerated(EnumType.STRING) //Enum 타입은 DB에 없기때문에 이 애노테이션 사용, STRING 으로 비교해야 나중에 중간에 값 들어갔을때 오류 안남
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP) //이건 나중에 BaseEntity 만들어서 Auditing 사용하면 됨
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob //VARCHAR를 넘어서는.. 큰 컨텐츠..?
    private String description;

    @Transient //이 데이터는 db에 쓰고싶지 않아.. 그냥 메모리에서만 관리하고싶어! 할 때 쓰는 애노테이션
    private int temp;

    protected Member() {
    }

}
