package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

//Junit4는 public 이어야 하지만 5버전 부터는 없어도 됨
class MemberRepositoryTest {

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach //test 후 초기화
    void afterEach() {
        memberRepository.clearStore();
        //만약 이거 주석처리하면 save() findAll() 이 섞여버림(초기화가 안돼~)듀
    }

    @Test
    void save() {
        //given
        Member member = new Member("hello", 20);
        //when
        Member savedMember =memberRepository.save(member);
        //then
        Member findMember = memberRepository.findById(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
    }

    @Test
    void findAll() {
        //given
        Member member1 =new Member("member1", 20);
        Member member2 =new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        List<Member> result = memberRepository.findAll();

        //then Assertions. 에 마우스 블록하고 alt + 엔터 후 Add-on~ static~
        assertThat(result.size()).isEqualTo(2);
        assertThat(result).contains(member1, member2);
    }
}