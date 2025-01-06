package core.spring.singleton;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import core.spring.AppConfig;
import core.spring.member.MemberService;

/**
 * 싱글톤 패턴
 * 클래스 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴.
 * 그래서 객체 인스턴스를 2개이상 생성하지 못하게 막아야함.
 *
 * ★ 싱글톤 패턴 설계시 주의점
 *  - 객체 인스턴스를 공유하므로 상태를 유지하도록 설계하면 안됨.
 *  - 특정 클라이언트에 의존적인 필드가 존재하면 X
 *  - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 X
 *  - 가급적 읽기만.
 *  - 필드 대신, 자바에 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용할 것.
 */
public class SingletonTest {

	/**
	 * 동일한 클래스 객체를 다중 생성 문제 TEST
	 */
	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer() {
		AppConfig appConfig = new AppConfig();

		// 1.조회: 호출할 때마다 객체를 생성
		MemberService memberService1 = appConfig.memberService();

		// 2.조회: 호출할 때마다 객체를 생성
		MemberService memberService2 = appConfig.memberService();

		// 참조값이 다른 것을 확인
		// 객체 다중 생성의 문제 => 메모리 낭비
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		assertThat(memberService1).isNotEqualTo(memberService2);
	}

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void singletonServiceTest() {
		SingletonService singletonService1 = SingletonService.getInstance();
		SingletonService singletonService2 = SingletonService.getInstance();

		System.out.println("singletonService1 = " + singletonService1);
		System.out.println("singletonService2 = " + singletonService2);

		// isSame : == (인스턴스 비교시)
		// isEqual : equals
		assertThat(singletonService1).isSameAs(singletonService2);
	}

	/**
	 * Spring Container에서 싱글톤 패턴의 단점을 완벽히 보완함.
	 */
	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		// 1.조회: 호출할 때마다 객체를 생성
		MemberService memberService1 = ac.getBean("memberService", MemberService.class);

		// 2.조회: 호출할 때마다 객체를 생성
		MemberService memberService2 = ac.getBean("memberService", MemberService.class);

		// 참조값이 다른 것을 확인
		// 객체 다중 생성의 문제 => 메모리 낭비
		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		assertThat(memberService1).isSameAs(memberService2);
	}
}
