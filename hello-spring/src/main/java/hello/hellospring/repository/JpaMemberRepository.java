package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //EntityManager가 알아서 DB연결 다 해줌
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Integer id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    //pk기반이 아닌 것들은 아래처럼 쿼리를 작성해주어야 함
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //Member Entity를 대상으로 조회를 함(not 테이블)
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
