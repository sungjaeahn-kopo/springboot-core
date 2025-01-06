package core.spring.singleton;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	@Test
	void statefulServiceSingleton() {
		AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = ac.getBean(StatefulService.class);
		StatefulService statefulService2 = ac.getBean(StatefulService.class);

		//ThreadA: A사용자가 10000원 주문
		int priceA = statefulService1.order("userA", 10000);
		//ThreadA: B사용자가 20000원 주문
		int priceB = statefulService1.order("userB", 20000);

		//ThreadA: 사용자 A 주문금액 조회 -> 사용자 B 주문금액 나오는 문제.
		// int price = statefulService1.getPrice();
		System.out.println("price = " + priceA);

		// Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}

}