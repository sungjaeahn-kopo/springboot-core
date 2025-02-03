package core.spring.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    // ac.getBean(MemberReposiotry.class)와 같은 역할
    @Autowired // 의존관계 자동 주입, @Component만 사용하면 의존성 주입이 안돼, 오류 발생
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // 테스트 용도 (싱글톤 패턴)
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
