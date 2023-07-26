package hello.hellospring.service;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    
    //property 파일보고 spring이 알아서 주입해줌
    //private DataSource dataSource;

    //@Autowired
    //public SpringConfig(DataSource dataSource){
    //    this.dataSource = dataSource;
    //}

    //private EntityManager em;

    //@Autowired
    //public SpringConfig(EntityManager em) {
    //    this.em = em;
    //}

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        //에러남
        //return new MemberService();
        return new MemberService(memberRepository);
    }

 //   @Bean
 //   public MemberRepository memberRepository() {
        //return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);

  //  }

    //정형화된 것이 아니기 때문에 AOP는 컴포넌트보다 빈설정으로 등록하는 걸 선호
    //@Bean
    //public TimeTraceAop timeTraceAop() {
    //    return new TimeTraceAop();
    //}
}
