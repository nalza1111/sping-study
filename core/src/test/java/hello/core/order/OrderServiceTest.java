package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderServiceTest {
    MemberService memberService;
    OrderService orderService;

    //test실행 전 실행
    @BeforeEach
    public void beforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder() {
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    @Test
    void fieldInjectionTest() {
        //테스트하려는 해당 impl의 필드주입을 사용할 수 없음...
        //1. 세터로 객체를 넣어줘야 함 => 세터 넣어야 함 => 번거로움
        //2. @Autowired OrderService orderService; => 스프링 띄워서 테스트
        //=>이건 @SpringBootTest있어야 가능
        //orderService.setMemberRepository(new MemoryMemberRepository());

        //OrderServiceImpl orderService = new OrderServiceImpl();
        //orderService.createOrder(1L, "itemA", 10000);
        //테스트 때도 어노테이션이나 스프링 툴들 마구 써야 함..순수한 JAVA 테스트 코드에선 하기 힘듬    }
    }

}