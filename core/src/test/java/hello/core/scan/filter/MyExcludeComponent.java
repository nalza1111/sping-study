package hello.core.scan.filter;

import java.lang.annotation.*;

//필드에 붙냐 클래스에 붙냐..TYPE=>클래스레벨에 붙음
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
}
