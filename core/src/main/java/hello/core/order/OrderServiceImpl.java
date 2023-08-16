package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    //할인정책 변경 => OrderServiceImpl.java 수정(DIP, OCP 위반)

    //이렇게 해결하자
    //세터주입은 setter떼야 함
    //@Autowired => 필드 주입
    private final MemberRepository memberRepository;
    //@Autowired => 필드주입
    private final DiscountPolicy  discountPolicy;

    //1개일 시 생략가능 (불변)
    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, /*@Qualifier("mainDiscountPolicy")*/
                            @MainDiscountPolicy DiscountPolicy discountPolicy) {
        System.out.println("생성자 주입");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
    //생성자 => 세터 주입 (싱글톤이기 때문에 같은 타입이 주입)
    //선택, 변경 가능성
    //finsal//finsal
    /*
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        System.out.println("세터 주입");
        this.memberRepository = memberRepository;
    }
    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
    */

    //일반 메서드 주입
    /*
    @Autowired
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }*/

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
