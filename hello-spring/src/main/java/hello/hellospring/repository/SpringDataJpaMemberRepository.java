package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.hibernate.metamodel.model.domain.internal.MapMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//쓰는 테이블과 pk의 데이터 유형
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Integer>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
