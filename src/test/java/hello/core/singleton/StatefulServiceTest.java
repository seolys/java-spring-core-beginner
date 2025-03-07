package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

	@Test
	@DisplayName("싱글톤 객체에서 값을 저장하면, Thread-Safe하지않는부분 확인.")
	void stateServiceSingletonTest() {
		ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
		StatefulService statefulService1 = context.getBean(StatefulService.class);
		StatefulService statefulService2 = context.getBean(StatefulService.class);

		// ThreadA: A사용자가 10000원 주문
		statefulService1.order("userA", 10000);

		// ThreadB: B사용자가 20000원 주문
		statefulService2.order("userB", 20000);

		// ThreadA: 사용자A 주문 금액 조회
		int price = statefulService1.getPrice();
		System.out.println("price = " + price);

		Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
	}

	static class TestConfig {

		@Bean
		public StatefulService statefulService() {
			return new StatefulService();
		}
	}

}