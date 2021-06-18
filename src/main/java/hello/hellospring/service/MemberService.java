package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//Component Scan 으로 자동 주입
//@Service

// JPA 를 사용하면 Service 계층에 Transactional 을 해주어야함
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    // DI 예제
//    @Autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    // 직접 Bean 주입
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /**
     * 회원가입
     * @param member
     * @return member.getId()
     */
    public Long join(Member member){
        // 같은 이름이 있는 중복 회원 불가
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();

    }

    /**
     * 전체 회원 조회
     */
    // 전체
    public List<Member> findMembers(){
            return memberRepository.findAll();
    }
    // Id별 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    /**
     * 같은 이름이 있는 중복 회원 불가 메소드
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> { // ifPresent : 값이 있으면
                throw new IllegalStateException("이미 존재하는 회원");
        });
    }

}
