package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


//@Service
//jpa사용 시 주의사항: Transactional이 있어야 함
@Transactional
public class MemberService {

    //private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    //의존성주입시 넣음
    //MemberService만 단독으로 불러도 memberRepository를 넣지 않아도
    //넣어줌
    //@Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     */
    public Integer join(Member member) {
        //같은 이름이 있는 중혹 회원X
        //변수 추출하기 [ Ctrl + Alt + v ]
        Optional<Member> result = memberRepository.findByName(member.getName());
        //result.get(); 은 널이 나올 수 있기 때문에 권장하지 않음
        //result.orElseGet(); 이것도 많이 씀
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        //아래 코드를 권장
        //중분회원검증
        //Refactor [ Ctrl + Alt + Shift + T ]
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent((m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }));
    }

    /**
     * 전체회원조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Integer memberId) {
        return memberRepository.findById(memberId);
    }
}
