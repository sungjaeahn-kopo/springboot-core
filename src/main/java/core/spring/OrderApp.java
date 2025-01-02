package core.spring;

import core.spring.member.Grade;
import core.spring.member.Member;
import core.spring.member.MemberService;
import core.spring.member.MemberServiceImpl;
import core.spring.order.Order;
import core.spring.order.OrderService;
import core.spring.order.OrderServiceImpl;

public class OrderApp {

    public static void main(String[] args) {
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();
        OrderService orderService = appConfig.orderService();

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order);
    }
}
