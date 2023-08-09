package hello.core;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok h = new HelloLombok();
        h.setAge(1);
        h.setName("sss");
        System.out.println(h);
    }
}
