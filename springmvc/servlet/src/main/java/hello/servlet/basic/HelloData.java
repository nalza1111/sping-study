package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HelloData {

    private String userName;
    private int age;

    @Override
    public String toString() {
        return "HelloData{" +
                "userName='" + userName + '\'' +
                ", age=" + age +
                '}';
    }
}
