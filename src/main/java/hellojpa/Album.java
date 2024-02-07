package hellojpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("A") //DTYPE 에 들어갈 이름 설정
public class Album extends Item {

    private String artist;
}
