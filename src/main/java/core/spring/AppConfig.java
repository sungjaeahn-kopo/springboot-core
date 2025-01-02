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

/**
 * DI Container(IoC Container)
 *
 * 역할과 구현의 분리
 * 어플리케이션의 전체 구성을 파악하기 좋음.
 */
public class AppConfig {

    // 리팩토링 단축키: ctrl + alt + m
    // 생성자 주입 방식
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    private static MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    /**
     * 정책이 변경되어도, 사용영역에서 변경하지 않고, 구성영역에서만(Appconfig) 변경하면 된다.
     * @return
     */
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
