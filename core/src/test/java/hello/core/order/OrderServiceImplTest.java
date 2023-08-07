package hello.core.order;

import org.junit.jupiter.api.Test;

public class OrderServiceImplTest {
    //순수자바로 테스트 짜기
    @Test
    void createOrder() {
        //생성자 지우고 테스트
        //OrderServiceImpl orderService = new OrderServiceImpl();
        //생성자 주입을 해두면 new OrderServiceImpl();여기서 컴파일 오류가 떠서 주입이 필요함을 알 수 있음
        //순수자바테스트 인자 만들어서 넣어줘야 함

        //orderService.createOrder(1L, "itemA", 10000);
        //=> NullPointerException ...repository와 DiscountPolicy를 잡아줘야 함
    }
}
