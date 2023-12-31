package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // => 오류가 나면 롤백
class MemberServiceIntegrationTest {


    /*
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach() {
        //Dependency Ijection(DI)
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach() {
        memberRepository.clearStore();
    }
    */

    //SpringBoot를 이용해 의존성 주입
    //DB인터페이스를 인스턴스로 변경 =>clearStore()를 썼던 건 메모리 초기화 때문이니까...필요없어짐
    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;

    @Test
    @Commit
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("jpa테스트222");

        //when
        Integer saveId = memberService.join(member);

        //then
        //Optional<Member> findMemberImsi = memberService.findOne(saveId);
        //바로 Optional에서 꺼내겠음
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        //assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        //assertThrows(NullPointerException.class, () -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");


/*        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            //12342무작위 숫자넣어서 발생하는 메시지확인해줌
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.13213");
        }
*/
        //then
    }

}