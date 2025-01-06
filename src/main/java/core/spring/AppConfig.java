package core.spring;

import core.spring.discount.DiscountPolicy;
import core.spring.discount.FixDiscountPolicy;
import core.spring.discount.RateDiscountPolicy;
import core.spring.member.MemberRepository;
import core.spring.member.MemberService;
import core.spring.member.MemberServiceImpl;
import core.spring.member.MemoryMemberRepository;
import core.spring.order.OrderService;
import core.spring.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * DI Container(IoC Container)
 *
 * 역할과 구현의 분리
 * 어플리케이션의 전체 구성을 파악하기 좋음.
 *
 * @Bean 어노테이션 추가시, spring container에 등록됨.
 */
@Configuration
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()
    // 이렇게 두번 호출해도 싱글톤 패턴이 깨지지 않는다.

    // 리팩토링 단축키: ctrl + alt + m
    // 생성자 주입 방식
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    // 해당 메서드에 static으로 되어있으면, Spring Container에서 싱글톤으로 유지 불가.
    // static 키워드가 붙으면, Spring Container가 아닌 JVM이 관리하게 됨.
    // static 키워드는 Spring Bean의 생명주기 관리에서 벗어나게 됨.
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 정책이 변경되어도, 사용영역에서 변경하지 않고, 구성영역에서만(Appconfig) 변경하면 된다.
     * @return
     */
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
