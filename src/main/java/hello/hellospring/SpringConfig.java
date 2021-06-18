package hello.hellospring;

import hello.hellospring.AOP.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 자바 코드로 직접 스프링빈을 등록하는 방법
 * 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야하면 설정을 토앻 스프링빈으로 등록
 */
@Configuration
public class SpringConfig {

//    private DataSource dataSource;

    // JPA 사용
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    // Spring Data Jpa 사용
    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean
//    public MemberService memberService() {
//        return new MemberService(memberRepository());
//    }
    // spring data jpa 를 위한 memberRepository 의존관계 Setting
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }


    // CPU Memory -> JDBC -> JdbcTemplate -> JPA -> Spring Data Jpa
//    @Bean
//    public MemberRepository memberRepository(){
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        return new JpaMemberRepository(entityManager);
//    }

    // AOP - TimeTraceAop 를 Spring Bean 으로 등록 (@Component 스캔하기로 했으니까 주석처리)
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }


}
