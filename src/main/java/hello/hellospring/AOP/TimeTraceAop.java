package hello.hellospring.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component // SpringConfig 에서 Bean 으로 등록해도되고 @Component 스캔으로 등록해도 되고
@Aspect // AOP임을 명시
public class TimeTraceAop {

    @Around("execution(* hello.hellospring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START: " + joinPoint.toString()); // 어떤 메소드를 Call 하는지
        try {
            return joinPoint.proceed(); // 다음 메소드로 진행
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString()+ " " + timeMs +
                    "ms");
        }
    }
}