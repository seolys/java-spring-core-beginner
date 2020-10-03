package hello.core.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigrationSigletonTest {

	@Test
	@DisplayName("@Configration Singleton test")
	void configurationTest() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
		OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);

		MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);
		MemberRepository memberRepository1 = memberService.getMemberRepository();
		MemberRepository memberRepository2 = orderService.getMemberRepository();
		System.out.println("memberRepository = " + memberRepository);
		System.out.println("memberRepository1 = " + memberRepository1);
		System.out.println("memberRepository2 = " + memberRepository2);

		assertThat(memberRepository).isSameAs(memberRepository1).isSameAs(memberRepository2);
	}

	@Test
	@DisplayName("")
	void configurationDeep() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
		AppConfig bean = ac.getBean(AppConfig.class);
		System.out.println("bean = " + bean);
	}

}
