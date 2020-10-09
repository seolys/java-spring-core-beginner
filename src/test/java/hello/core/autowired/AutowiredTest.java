package hello.core.autowired;

import hello.core.member.Member;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

public class AutowiredTest {

	@Test
	void AutowiredOption() {
		ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
	}

	static class TestBean {

		@Autowired(required = false)
		public void setNoBean1(Member setNoBean1) {
			System.out.println("setNoBean1 = " + setNoBean1);
		}

		@Autowired
		public void setNoBean2(@Nullable Member setNoBean2) {
			System.out.println("setNoBean2 = " + setNoBean2);
		}

		@Autowired
		public void setNoBean3(Optional<Member> setNoBean3) {
			System.out.println("setNoBean3 = " + setNoBean3);
		}

	}

}
