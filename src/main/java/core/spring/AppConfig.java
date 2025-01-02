package core.spring;

import core.spring.discount.FixDiscountPolicy;
import core.spring.member.MemberService;
import core.spring.member.MemberServiceImpl;
import core.spring.member.MemoryMemberRepository;
import core.spring.order.OrderService;
import core.spring.order.OrderServiceImpl;

public class AppConfig {

    // 생성자 주입 방식
    public MemberService memberService() {
        return new MemberServiceImpl(new MemoryMemberRepository());
    }

    public OrderService orderService() {
        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
    }
}
