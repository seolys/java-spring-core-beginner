package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SingletonTest {

	@Test
	@DisplayName("스프링 없는 순수한 DI 컨테이너")
	void pureContainer() {
		// given
		AppConfig appConfig = new AppConfig();
		MemberService memberService1 = appConfig.memberService();
		MemberService memberService2 = appConfig.memberService();

		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		assertThat(memberService1).isNotSameAs(memberService2);
	}

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void singletonServiceTest() {
		SingletonService instance1 = SingletonService.getInstance();
		SingletonService instance2 = SingletonService.getInstance();
		System.out.println("instance1 = " + instance1);
		System.out.println("instance2 = " + instance2);
		assertThat(instance1).isSameAs(instance2);
	}

	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberService memberService1 = context.getBean("memberService", MemberService.class);
		MemberService memberService2 = context.getBean("memberService", MemberService.class);

		System.out.println("memberService1 = " + memberService1);
		System.out.println("memberService2 = " + memberService2);

		assertThat(memberService1).isSameAs(memberService2);
	}

}
