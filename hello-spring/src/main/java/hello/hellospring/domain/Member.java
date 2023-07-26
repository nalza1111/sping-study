package hello.hellospring.domain;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
public class Member {

    //DB가 생성해주는 ID(시퀸스)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
