package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Interface 가 Interface 를 받을 땐 extends
// JpaRepository 를 받는 이유는
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
    // JPQL : select m from Member m where m.name = ?
    // 자동으로 JPQL 생성

}