package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 빈으로 자동 등록
@ComponentScan(
        //뺄 것을 지정
        //기존 AppConfig와 TestConfig 등... 코드 공부때문에 남겨 놨는데
        //빼지 않으면 자동등록되버려(Configuration은 @Component붙어있어서 자동스캔됨) 충돌남
        //AppConfig의 클래스는 Configuration
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

}
