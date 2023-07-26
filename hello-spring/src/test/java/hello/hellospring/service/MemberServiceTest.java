package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    //테스트는 한글로도 많이 적음
    MemberService memberService;
    //MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    //staic이라 상관은 없지만...repository를 초기화해서 쓰는 듯 해 찝찝함
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
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

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

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}