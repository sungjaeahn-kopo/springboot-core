package core.spring.order;

import core.spring.discount.DiscountPolicy;
import core.spring.discount.FixDiscountPolicy;
import core.spring.discount.RateDiscountPolicy;
import core.spring.member.Member;
import core.spring.member.MemberRepository;
import core.spring.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;
    /**
     * DIP 위배
     * OrderSerivce가 DiscountPolicy를 참조하고 있지만, DiscountPolicy의 구현체인 FixDiscountPolicy도 의존하므로.
     *
     * OCP 위반
     * 정책 변경시, orderserivceImpl에 구현체도 수정해야됨.
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    /**
     * 추상체만 선언하면 DIP 준수하지만, NullPointException 발생.
     * 따라서, 전체 동작 방식을 결정하는 Appconfig로 구현 객체 생성, 연결하는 설정 클래스 생성 필요.
     */
    private final DiscountPolicy discountPolicy;

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도 (싱글톤 패턴)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
