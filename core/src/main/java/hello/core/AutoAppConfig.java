package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
// 빈으로 자동 등록
@ComponentScan(
        //member Package만 컴포넌트 스캔
        basePackages = "hello.core.member",
        //이 폴더부터 찹음
        //관례상 basePackages basePackageClasses 붙이지 않고 이 클래스가 위치한 패키지(hello.core)이하로 스캔 함
        //스프링부트는 @SpringBootApplication에 컴포넌트 스캔이 있으므로 자동 빈등록 됨..즉 @ComponentScan을 쓸 이유가 없음
        basePackageClasses = AutoAppConfig.class,
        //뺄 것을 지정
        //기존 AppConfig와 TestConfig 등... 코드 공부때문에 남겨 놨는데
        //빼지 않으면 자동등록되버려(Configuration은 @Component붙어있어서 자동스캔됨) 충돌남
        //AppConfig의 클래스는 Configuration
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    //수동빈등록.. 자동빈등록과 충돌하니 꺼두자
    /*@Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }*/
}
