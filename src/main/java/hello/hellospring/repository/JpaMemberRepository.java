package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    // JPA 는 EntityManager 라는 걸로 모든 게 동작됨
    // 고로 EntityManager 를 Injection 받아야함
    private final EntityManager em;
    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    public Member save(Member member) {
        // persist -> 영속하다, 영구저장한다의 뜻
        // Insert 쿼리 만들어서 집어넣음
        em.persist(member);
        return member;
    }

    public Optional<Member> findById(Long id) {
        // pk 값은 find 메소드로 조회 가능
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    public List<Member> findAll() {
        // pk 값이 아닌 조회조건은 jpql 이라는 객체지향 쿼리언어를 사용해야함
        // 차이는
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }

    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
}